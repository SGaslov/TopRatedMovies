package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.android.gaslov.topratedmovies.R

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragmentContainer, MovieListFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            viewModel.getMovieList()
        }
    }
}