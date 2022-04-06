package com.android.gaslov.topratedmovies.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.domain.Movie
import com.squareup.picasso.Picasso

class MovieAdapter (private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onClickListener: ((Int) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val genresTextView: TextView
        val ratingTextView: TextView
        val posterImageView: ImageView

        init {
            with(view) {
                titleTextView = findViewById(R.id.titleTextView)
                genresTextView = findViewById(R.id.genresTextView)
                ratingTextView = findViewById(R.id.ratingTextView)
                posterImageView = findViewById(R.id.posterImageView)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = movieList[position]
        viewHolder.apply {
            titleTextView.text = movie.title
            genresTextView.text = movie.genres
            ratingTextView.text = movie.rating

            Picasso
                .get()
                .load(POSTER_BASE_URL + movie.posterPath)
                .into(posterImageView)
        }

        viewHolder.itemView.setOnClickListener {
            onClickListener?.invoke(movie.movieId)
        }
    }

    override fun getItemCount() = movieList.size

    companion object {

        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w200"
    }

}
