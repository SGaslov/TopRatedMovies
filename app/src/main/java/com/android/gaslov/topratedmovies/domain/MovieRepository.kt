package com.android.gaslov.topratedmovies.domain

import androidx.lifecycle.LiveData

interface MovieRepository {

    fun getMovie(movieId: Int): LiveData<Movie>

    fun getMovieList(): LiveData<List<Movie>>
}