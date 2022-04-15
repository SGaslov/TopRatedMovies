package com.android.gaslov.topratedmovies.di

import android.app.Application
import com.android.gaslov.topratedmovies.data.MovieRepositoryImpl
import com.android.gaslov.topratedmovies.data.database.MovieDatabase
import com.android.gaslov.topratedmovies.data.network.api.MovieApiFactory
import com.android.gaslov.topratedmovies.data.network.api.MovieApiService
import com.android.gaslov.topratedmovies.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {

        @Provides
        fun provideApiService(): MovieApiService {
            return MovieApiFactory.service
        }

        @Provides
        fun provideDb(application: Application): MovieDatabase {
            return MovieDatabase.getInstance(application)
        }
    }

}