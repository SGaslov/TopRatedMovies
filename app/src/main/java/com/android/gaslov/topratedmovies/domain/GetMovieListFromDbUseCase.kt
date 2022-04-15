package com.android.gaslov.topratedmovies.domain

import javax.inject.Inject

class GetMovieListFromDbUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke() = repository.getMovieListFromDb()
}