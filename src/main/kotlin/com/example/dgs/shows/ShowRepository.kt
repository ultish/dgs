package com.example.dgs.shows

import com.example.dgs.entities.Show
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor


interface ShowRepository : MongoRepository<Show, String>,
    QuerydslPredicateExecutor<Show> {

    fun findByTitle(title: String): Show

    fun findByTitleAndReleaseYear(title: String?, releaseYear: Int?): List<Show>

}