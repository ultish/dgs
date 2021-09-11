package com.example.dgs.directors

import com.example.dgs.generated.types.Director

interface DirectorsService {
    fun directors(keys: List<String>?): List<Director>

    fun directorForShows(showIds: List<String>): Map<String, Director>
}