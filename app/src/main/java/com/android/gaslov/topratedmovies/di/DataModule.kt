package com.android.gaslov.topratedmovies.di

import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl
import com.android.gaslov.topratedmovies.domain.MovieRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}