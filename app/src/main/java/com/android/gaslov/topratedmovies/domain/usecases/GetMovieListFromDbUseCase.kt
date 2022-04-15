package com.android.gaslov.topratedmovies.domain.usecases

import com.android.gaslov.topratedmovies.domain.MovieRepository
import javax.inject.Inject

class GetMovieListFromDbUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke() = repository.getMovieListFromDb()
}