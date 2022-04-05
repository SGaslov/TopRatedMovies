package com.android.gaslov.topratedmovies.presentation

import androidx.lifecycle.ViewModel
import com.android.gaslov.topratedmovies.domain.GetMovieListUseCase
import com.android.gaslov.topratedmovies.domain.GetMovieUseCase

class MovieViewModel : ViewModel() {

    private val getMovieListUseCase = GetMovieListUseCase()
    private val getMovieUseCase = GetMovieUseCase()

    fun getMovieList() = getMovieListUseCase()

    fun getMovieDetail(movieId: Int) = getMovieUseCase(movieId)
}