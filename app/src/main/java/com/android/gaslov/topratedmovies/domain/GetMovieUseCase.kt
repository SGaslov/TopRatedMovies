package com.android.gaslov.topratedmovies.domain

import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl

class GetMovieUseCase() {

    private val repository: MovieRepository = MovieRepositoryImpl()

    operator fun invoke(movieId: Int) = repository.getMovie(movieId)
}