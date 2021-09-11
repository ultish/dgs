package com.example.dgs.shows

import com.example.dgs.generated.types.Show
import org.springframework.stereotype.Service

@Service
class BasicShowsService : ShowsService {
    override fun shows(): List<Show> {
        
        return listOf(
            Show(id = "1", title = "Stranger Things", releaseYear = 2016),
            Show(
                id = "2", "Ozark ", 2017
            ),
            Show(
                id = "3", "The Crown", 2016
            ),
            Show(
                id = "4", "Dead to Me", 2019
            ),
            Show(id = "5", "Orange is the New Black ", 2013)
        )

    }
}