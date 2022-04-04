package com.android.gaslov.topratedmovies.domain

class GetMovieListUseCase(private val repository: MovieRepository) {

    fun getMovieListUseCase(): MutableList<Movie> {
        return repository.getMovieList()
    }
}