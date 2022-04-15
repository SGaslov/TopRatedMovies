package com.android.gaslov.topratedmovies.domain

import javax.inject.Inject

class GetMovieDetailFromWebUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(movieId: Int) = repository.getMovieDetailFromWeb(movieId)
}