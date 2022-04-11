package com.android.gaslov.topratedmovies.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.gaslov.topratedmovies.domain.Movie

class MovieDiffCallback(): DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}