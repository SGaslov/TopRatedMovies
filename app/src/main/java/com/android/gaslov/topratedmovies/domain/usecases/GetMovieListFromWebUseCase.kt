package com.android.gaslov.topratedmovies.domain.usecases

import com.android.gaslov.topratedmovies.domain.MovieRepository
import javax.inject.Inject

class GetMovieListFromWebUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(page: Int) = repository.getMovieListFromWeb(page)
}