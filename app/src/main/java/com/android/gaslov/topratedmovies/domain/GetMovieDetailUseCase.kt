package com.android.gaslov.topratedmovies.domain

import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl

class GetMovieDetailUseCase() {

    private val repository: MovieRepository = MovieRepositoryImpl()

    suspend operator fun invoke(movieId: Int) = repository.getMovieDetail(movieId)
}