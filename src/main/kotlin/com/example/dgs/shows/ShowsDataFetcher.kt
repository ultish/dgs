package com.example.dgs.shows

import com.example.dgs.generated.types.Show
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class ShowsDataFetcher {

    @Autowired
    lateinit var showsService: ShowsService

    @DgsQuery
    fun shows(@InputArgument titleFilter: String?): List<Show> {
        println("Fetching Shows... $titleFilter")
        return if (titleFilter != null) {
            showsService.shows()
                .filter { it: Show -> it.title.contains(titleFilter) }
        } else {
            showsService.shows()
        }
    }

}