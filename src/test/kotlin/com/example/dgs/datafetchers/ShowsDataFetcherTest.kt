package com.example.dgs.datafetchers

import com.example.dgs.generated.client.ShowsGraphQLQuery
import com.example.dgs.generated.client.ShowsProjectionRoot
import com.example.dgs.generated.types.Show
import com.example.dgs.shows.ShowsDataFetcher
import com.example.dgs.shows.ShowsService
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(
    classes = [DgsAutoConfiguration::class,
        ShowsDataFetcher::class]
)
class ShowsDataFetcherTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockBean
    lateinit var showsService: ShowsService

    @BeforeEach
    fun before() {
        Mockito.`when`(showsService.shows()).thenAnswer {
            listOf(Show("mock title", 2020))
        }
    }

    @Test
    fun shows() {
        val titles: List<String> = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            {
                shows {
                    title
                    releaseYear
                }
            }
            """.trimIndent(), "data.shows[*].title"
        )
        assertThat(titles).contains("mock title")
    }

    @Test
    fun showsWithQueryApi() {

        val graphQLQueryRequest = GraphQLQueryRequest(
            ShowsGraphQLQuery
                .Builder()
                .titleFilter("mock")
                .build(), ShowsProjectionRoot().title()
        )

        val titles = dgsQueryExecutor.executeAndExtractJsonPath<List<String>>(
            graphQLQueryRequest.serialize(),
            "data.shows[*].title"
        )
        assertThat(titles).contains("mock title")
    }

    @Test
    fun showsWithException() {
        Mockito.`when`(showsService.shows()).thenThrow(
            RuntimeException
                ("nothing to see here")
        )

        val result = dgsQueryExecutor.execute(
            """
                {
                    shows {
                        title
                        releaseYear
                    }
                }
            """.trimIndent()
        )

        assertThat(result.errors).isNotEmpty
        assertThat(result.errors[0].message).isEqualTo("java.lang.RuntimeException: nothing to see here")
    }
}