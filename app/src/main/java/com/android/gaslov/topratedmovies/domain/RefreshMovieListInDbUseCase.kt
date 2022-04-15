package com.android.gaslov.topratedmovies.domain

import android.app.Application
import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl

class RefreshMovieListInDbUseCase(application: Application) {

    private val repository: MovieRepository = MovieRepositoryImpl(application)

    suspend operator fun invoke(movieList: List<Movie>) = repository.refreshMovieListInDb(movieList)
}