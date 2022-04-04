package com.android.gaslov.topratedmovies.domain

interface MovieRepository {

    fun getMovie(movieId: Int): Movie

    fun getMovieList(): MutableList<Movie>
}