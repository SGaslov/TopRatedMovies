package com.android.gaslov.topratedmovies.presentation

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.gaslov.topratedmovies.domain.*
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val getMovieUseCase = GetMovieDetailFromWebUseCase(application)
    private val getTotalPagesUseCase = GetTotalPagesUseCase(application)
    private val getMovieListUseCase = GetMovieListFromWebUseCase(application)
    private val getMovieListFromDbUseCase = GetMovieListFromDbUseCase(application)
    private val insertMovieListToDbUseCase = InsertMovieListToDbUseCase(application)
    private val refreshMovieListInDbUseCase = RefreshMovieListInDbUseCase(application)

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    @OptIn(androidx.paging.ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = MovieRemoteMediator(
            getTotalPagesUseCase,
            getMovieListUseCase,
            insertMovieListToDbUseCase,
            refreshMovieListInDbUseCase
        )
    ) {
        getMovieListFromDbUseCase()
    }.flow.cachedIn(viewModelScope)

    fun loadMovieDetailInfo(movieId: Int) {
        viewModelScope.launch {
            val movieDetail = getMovieUseCase(movieId)
            _movieDetail.value = movieDetail
        }
    }
}