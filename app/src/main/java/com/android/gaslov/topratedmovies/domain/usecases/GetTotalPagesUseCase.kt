package com.android.gaslov.topratedmovies.domain.usecases

import com.android.gaslov.topratedmovies.domain.MovieRepository
import javax.inject.Inject

class GetTotalPagesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke() = repository.getTotalPagesFromWeb()
}