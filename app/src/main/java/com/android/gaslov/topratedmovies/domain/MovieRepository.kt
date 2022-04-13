package com.android.gaslov.topratedmovies.domain

interface MovieRepository {

    suspend fun getMovieDetail(movieId: Int): Movie

    suspend fun getMovieList(page: Int): List<Movie>

    suspend fun getTotalPages(): Int
}