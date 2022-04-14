package com.android.gaslov.topratedmovies.presentation

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.gaslov.topratedmovies.data.MovieRemoteMediator
import com.android.gaslov.topratedmovies.data.database.MovieDatabase
import com.android.gaslov.topratedmovies.domain.GetMovieDetailUseCase
import com.android.gaslov.topratedmovies.domain.GetMovieListUseCase
import com.android.gaslov.topratedmovies.domain.GetTotalPagesUseCase
import com.android.gaslov.topratedmovies.domain.Movie
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val getMovieUseCase = GetMovieDetailUseCase()
    private val getTotalPagesUseCase = GetTotalPagesUseCase()
    private val getMovieListUseCase = GetMovieListUseCase()

    private val db = MovieDatabase.getInstance(application)

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    @OptIn(androidx.paging.ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = MovieRemoteMediator(db, getTotalPagesUseCase, getMovieListUseCase)
    ) {
        db.movieDao().getMovieList()
    }.flow.cachedIn(viewModelScope)

    fun loadMovieDetailInfo(movieId: Int) {
        viewModelScope.launch {
            val movieDetail = getMovieUseCase(movieId)
            _movieDetail.value = movieDetail
        }
    }
}