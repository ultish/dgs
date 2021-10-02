package com.example.dgs.shows

import com.example.dgs.entities.QShow
import com.example.dgs.entities.toTO
import com.example.dgs.generated.types.Show
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.querydsl.core.BooleanBuilder
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class ShowsDataFetcher {

    @Autowired
    lateinit var showsService: ShowsService

    @Autowired
    lateinit var showRepository: ShowRepository

    @DgsQuery
    fun shows(
        @InputArgument title: String?, // arg name must match schema file
        @InputArgument releaseYear: Int?
    ): List<Show> {
        println("Fetching Shows... $title, $releaseYear")

        val builder = BooleanBuilder()

        title?.also {
            builder.and(QShow.show.title.equalsIgnoreCase(it))
        }.let {
            println("title filter: $it")
        }

        releaseYear?.let {
            builder.and(QShow.show.releaseYear.gt(it))
        }

        return showRepository.findAll(builder).map { it.toTO() }
    }
}
