package com.android.gaslov.topratedmovies.presentation

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.gaslov.topratedmovies.domain.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieDetailFromWebUseCase,
    private val getTotalPagesUseCase: GetTotalPagesUseCase,
    private val getMovieListUseCase: GetMovieListFromWebUseCase,
    private val getMovieListFromDbUseCase: GetMovieListFromDbUseCase,
    private val insertMovieListToDbUseCase: InsertMovieListToDbUseCase,
    private val refreshMovieListInDbUseCase: RefreshMovieListInDbUseCase
) : ViewModel() {

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