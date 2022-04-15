package com.android.gaslov.topratedmovies.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.databinding.MovieItemBinding
import com.android.gaslov.topratedmovies.domain.Movie

class MovieAdapter : PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding: MovieItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.movie_item,
            viewGroup,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        val binding = viewHolder.binding
        binding.movie = movie
    }
}
