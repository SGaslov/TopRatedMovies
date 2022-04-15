package com.android.gaslov.topratedmovies.domain

import javax.inject.Inject

class RefreshMovieListInDbUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(movieList: List<Movie>) = repository.refreshMovieListInDb(movieList)
}