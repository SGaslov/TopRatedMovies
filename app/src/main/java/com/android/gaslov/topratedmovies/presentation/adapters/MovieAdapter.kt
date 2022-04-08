package com.android.gaslov.topratedmovies.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.databinding.MovieItemBinding
import com.android.gaslov.topratedmovies.domain.Movie

class MovieAdapter (private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onClickListener: ((Int) -> Unit)? = null

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding: MovieItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = movieList[position]
        val binding = viewHolder.binding
        binding.movie = movie

        viewHolder.itemView.setOnClickListener {
            onClickListener?.invoke(movie.movieId)
        }
    }

    override fun getItemCount() = movieList.size
}
