package com.android.gaslov.topratedmovies.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.databinding.MovieItemBinding
import com.android.gaslov.topratedmovies.domain.Movie

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffCallback()) {

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClickListener?.invoke(binding.movie?.movieId!!)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding: MovieItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.movie_item,
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = getItem(position)
        val binding = viewHolder.binding
        binding.movie = movie
    }

    companion object {

        var onClickListener: ((Int) -> Unit)? = null
    }
}
