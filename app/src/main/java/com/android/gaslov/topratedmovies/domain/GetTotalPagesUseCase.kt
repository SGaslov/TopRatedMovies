package com.android.gaslov.topratedmovies.domain

import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl

class GetTotalPagesUseCase {

    private val repository: MovieRepository = MovieRepositoryImpl()

    suspend operator fun invoke() = repository.getTotalPages()
}