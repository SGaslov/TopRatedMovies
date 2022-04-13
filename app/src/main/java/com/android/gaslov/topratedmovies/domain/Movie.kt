package com.android.gaslov.topratedmovies.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val movieId: Int,
    val budget: String,
    val genres: String,
    val overview: String,
    val page: Int,
    val popularity: String,
    val posterPath: String,
    val productionCountries: String,
    val title: String,
    val rating: String
)

//TODO: do I need special Movie entity for database?
