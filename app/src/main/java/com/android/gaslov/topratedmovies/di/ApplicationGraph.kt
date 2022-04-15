package com.android.gaslov.topratedmovies.di

import android.app.Application
import com.android.gaslov.topratedmovies.presentation.MovieDetailFragment
import com.android.gaslov.topratedmovies.presentation.MovieListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationGraph {

    fun inject(fragment: MovieListFragment)

    fun inject(fragment: MovieDetailFragment)

    fun inject(application: Application)

    @Component.Factory
    interface  Factory {

        fun create(@BindsInstance application: Application): ApplicationGraph
    }
}