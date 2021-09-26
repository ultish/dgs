package com.example.dgs.entities

import com.querydsl.core.annotations.QueryEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
@QueryEntity // this is required otherwise querydsl won't generate Qclass
data class Show(
    @Id
    val id: String,
    val title: String,
    val releaseYear: Int
)

fun Show.toTO() = com.example.dgs.generated.types.Show(id, title, releaseYear)