package com.android.gaslov.topratedmovies.domain

interface MovieRepository {

    suspend fun getMovie(movieId: Int): Movie

    suspend fun getMovieList(): List<Movie>
}