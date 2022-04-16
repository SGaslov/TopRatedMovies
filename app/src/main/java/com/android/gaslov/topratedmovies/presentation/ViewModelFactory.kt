package com.android.gaslov.topratedmovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.gaslov.topratedmovies.domain.usecases.GetMovieDetailFromWebUseCase
import com.android.gaslov.topratedmovies.domain.usecases.GetMovieListFromDbUseCase
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
    private val getMovieDetailFromWebUseCase: GetMovieDetailFromWebUseCase,
    private val getMovieListFromDbUseCase: GetMovieListFromDbUseCase,
    private val remoteMediator: MovieRemoteMediator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetMovieDetailFromWebUseCase::class.java,
            GetMovieListFromDbUseCase::class.java,
            MovieRemoteMediator::class.java
        ).newInstance(
            getMovieDetailFromWebUseCase,
            getMovieListFromDbUseCase,
            remoteMediator
        )
    }
}