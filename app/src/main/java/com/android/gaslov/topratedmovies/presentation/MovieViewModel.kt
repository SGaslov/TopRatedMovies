package com.android.gaslov.topratedmovies.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.gaslov.topratedmovies.domain.GetMovieListUseCase
import com.android.gaslov.topratedmovies.domain.GetMovieUseCase

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val getMovieListUseCase = GetMovieListUseCase()
    private val getMovieUseCase = GetMovieUseCase()

    fun getMovieList() = getMovieListUseCase()

    fun getMovieDetail(movieId: Int) = getMovieUseCase(movieId)
}