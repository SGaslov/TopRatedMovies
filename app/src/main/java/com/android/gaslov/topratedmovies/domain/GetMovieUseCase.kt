package com.android.gaslov.topratedmovies.domain

class GetMovieUseCase(private val repository: MovieRepository) {

    fun getMovieUseCase(movieId: Int): Movie {
        return repository.getMovie(movieId)
    }
}