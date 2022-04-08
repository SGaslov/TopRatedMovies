package com.android.gaslov.topratedmovies.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

private const val BIG_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w300"
private const val SMALL_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w200"

@BindingAdapter("bigPosterEndpointUrl")
fun loadBigImage(view: ImageView, posterPath: String?) {
    posterPath?.let {
        Picasso
            .get()
            .load(BIG_POSTER_BASE_URL + posterPath)
            .into(view)
    }
}

@BindingAdapter("smallPosterEndpointUrl")
fun loadSmallImage(view: ImageView, posterPath: String?) {
    posterPath?.let {
        Picasso
            .get()
            .load(SMALL_POSTER_BASE_URL + posterPath)
            .into(view)
    }
}