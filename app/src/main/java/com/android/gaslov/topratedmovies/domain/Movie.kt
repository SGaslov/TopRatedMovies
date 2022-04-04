package com.android.gaslov.topratedmovies.domain

data class Movie(
    val budget: Int = 0,
    val genres: List<String>,
    val overview: String = "",
    val popularity: Double,
    val posterPath: String,
    val productionCountries: List<String> = listOf(),
    val title: String,
    val rating: Double
)
