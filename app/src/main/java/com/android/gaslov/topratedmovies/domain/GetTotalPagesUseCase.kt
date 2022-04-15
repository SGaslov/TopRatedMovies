package com.android.gaslov.topratedmovies.domain

import android.app.Application
import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl

class GetTotalPagesUseCase(application: Application) {

    private val repository: MovieRepository = MovieRepositoryImpl(application)

    suspend operator fun invoke() = repository.getTotalPagesFromWeb()
}