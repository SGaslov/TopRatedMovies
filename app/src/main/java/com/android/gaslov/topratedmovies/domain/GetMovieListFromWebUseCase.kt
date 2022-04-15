package com.android.gaslov.topratedmovies.domain

import javax.inject.Inject

class GetMovieListFromWebUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(page: Int) = repository.getMovieListFromWeb(page)
}