package com.example.dgs.shows

import com.example.dgs.entities.toTO
import com.example.dgs.generated.types.Show
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class ShowsDataFetcher {

    @Autowired
    lateinit var showsService: ShowsService

    @Autowired
    lateinit var showRepository: ShowRepository

    @DgsQuery
    fun shows(
        @InputArgument titleFilter: String?,
        @InputArgument releaseYearFilter: Int?
    ): List<Show> {
        println("Fetching Shows... $titleFilter")

        return if (titleFilter != null) {
            showRepository.findByTitleAndReleaseYear(
                titleFilter,
                releaseYearFilter
            ).map { it.toTO() }

//            val p: Predicat
//            showRepository.findAll(com.querydsl.core.types.Predicate())

        } else {
            showsService.shows()
        }
    }

}