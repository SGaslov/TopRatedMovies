package com.android.gaslov.topratedmovies.domain

import android.app.Application
import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl

class GetMovieListFromDbUseCase(application: Application) {

    private val repository: MovieRepository = MovieRepositoryImpl(application)

    operator fun invoke() = repository.getMovieListFromDb()
}