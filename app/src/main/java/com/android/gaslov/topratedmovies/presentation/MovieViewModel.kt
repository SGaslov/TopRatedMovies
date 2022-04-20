package com.android.gaslov.topratedmovies.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.gaslov.topratedmovies.domain.*
import com.android.gaslov.topratedmovies.domain.usecases.GetMovieDetailFromWebUseCase
import com.android.gaslov.topratedmovies.domain.usecases.GetMovieListFromDbUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MovieViewModel(
    private val getMovieDetailFromWebUseCase: GetMovieDetailFromWebUseCase,
    private val getMovieListFromDbUseCase: GetMovieListFromDbUseCase,
    private val remoteMediator: MovieRemoteMediator,
    private val application: Application
) : ViewModel() {

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    @OptIn(androidx.paging.ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = remoteMediator
    ) {
        getMovieListFromDbUseCase()
    }.flow.cachedIn(viewModelScope)

    fun loadMovieDetailInfo(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetail = getMovieDetailFromWebUseCase(movieId)
                _movieDetail.value = movieDetail
            } catch (error: Exception) {
                if (error is HttpException) {
                    Toast.makeText(
                        application,
                        "Server connection problem ${error.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}