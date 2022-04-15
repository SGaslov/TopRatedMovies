package com.android.gaslov.topratedmovies.domain

import javax.inject.Inject

class InsertMovieListToDbUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(movieList: List<Movie>) = repository.insertMovieListToDb(movieList)
}