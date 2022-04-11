package com.android.gaslov.topratedmovies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.gaslov.topratedmovies.domain.GetMovieListUseCase
import com.android.gaslov.topratedmovies.domain.GetMovieUseCase
import com.android.gaslov.topratedmovies.domain.Movie
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val getMovieListUseCase = GetMovieListUseCase()
    private val getMovieUseCase = GetMovieUseCase()

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    fun getMovieList(page: Int) {
        viewModelScope.launch {
            val movieList = getMovieListUseCase(page)
            _movieList.value = movieList
        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val movieDetail = getMovieUseCase(movieId)
            _movieDetail.value = movieDetail
        }
    }
}