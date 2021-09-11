package com.example.dgs.datafetchers

import com.example.dgs.generated.types.Show
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class ShowDataFetcher {
    private val shows = listOf(
        Show(title = "Stranger Things", releaseYear = 2016),
        Show("Ozark", 2017),
        Show("The Crown", 2016),
        Show("Dead to Me", 2019),
        Show("Orange is the New Black", 2013)
    )

    @DgsQuery
    fun shows(@InputArgument titleFilter: String?): List<Show> {
        return if (titleFilter != null) {
            // playing with kotlin lambda syntax
            //shows.filter { it:Show -> it.title.contains(titleFilter) }
            val lambda = { show: Show -> show.title.contains(titleFilter) }
            val lambda2 = { show: Show ->
                val title = show.title
                title.contains(titleFilter)
            }
//            shows.filter { it: Show -> it.title.contains(titleFilter) }
            shows.filter(lambda2)
        } else {
            shows
        }
    }

}