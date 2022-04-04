package com.android.gaslov.topratedmovies.domain

import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl

class GetMovieListUseCase() {

    private val repository: MovieRepository = MovieRepositoryImpl()

    operator fun invoke() = repository.getMovieList()
}