package com.android.gaslov.topratedmovies.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.databinding.MovieItemBinding

class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
           onClickListener?.invoke(binding.movie?.movieId!!)
        }
    }

    companion object {

        var onClickListener: ((Int) -> Unit)? = null
    }
}