package com.android.gaslov.topratedmovies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.gaslov.topratedmovies.data.paging.MoviePagingSource
import com.android.gaslov.topratedmovies.domain.GetMovieUseCase
import com.android.gaslov.topratedmovies.domain.Movie
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val getMovieUseCase = GetMovieUseCase()

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        MoviePagingSource()
    }.flow.cachedIn(viewModelScope)

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val movieDetail = getMovieUseCase(movieId)
            _movieDetail.value = movieDetail
        }
    }
}