package com.example.dgs.directors

import com.example.dgs.generated.types.Director
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class BasicDirectorsService : DirectorsService {
    val directors = mutableListOf<Director>()

    val showToDirectors = mutableMapOf<String, Director>()

    @PostConstruct
    fun createStuff() {
        directors.addAll(
            listOf(
                Director(id = "1", name = "Director A"),
                Director(id = "2", name = "Director B"),
                Director(id = "3", name = "Director C"),
                Director(id = "4", name = "Director D"),
            )
        )

        showToDirectors.put("1", directors[0])
        showToDirectors.put("2", directors[1])
        showToDirectors.put("3", directors[2])
        showToDirectors.put("4", directors[3])
        showToDirectors.put("5", directors[0])

    }

    override fun directors(keys: List<String>?): List<Director> {
        println("Fetching directors... $keys")
        return if (keys != null) {
            directors.filter { keys.contains(it.id) }
        } else {
            directors
        }
    }

    override fun directorForShows(showIds: List<String>): Map<String, Director> {
        println("Fetching directors for shows $showIds")
        return showToDirectors.filter { showIds.contains(it.key) }
    }

}