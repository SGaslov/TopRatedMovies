package com.android.gaslov.topratedmovies.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.domain.Movie

class MovieAdapter (private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onClickListener: (() -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val genresTextView: TextView
        val ratingTextView: TextView

        init {
            with(view) {
                titleTextView = findViewById(R.id.titleTextView)
                genresTextView = findViewById(R.id.genresTextView)
                ratingTextView = findViewById(R.id.ratingTextView)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            titleTextView.text = movieList[position].title
            genresTextView.text = movieList[position].genres
            ratingTextView.text = movieList[position].rating
        }

        viewHolder.itemView.setOnClickListener {
            onClickListener?.invoke()
        }
    }

    override fun getItemCount() = movieList.size

}
