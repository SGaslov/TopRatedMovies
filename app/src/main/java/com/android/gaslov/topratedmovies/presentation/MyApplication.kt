package com.android.gaslov.topratedmovies.presentation

import android.app.Application
import com.android.gaslov.topratedmovies.di.ApplicationGraph
import com.android.gaslov.topratedmovies.di.DaggerApplicationGraph

class MyApplication : Application() {

    val appGraph: ApplicationGraph by lazy {
        DaggerApplicationGraph.factory().create(this)
    }

    override fun onCreate() {
        appGraph.inject(this)
        super.onCreate()
    }
}