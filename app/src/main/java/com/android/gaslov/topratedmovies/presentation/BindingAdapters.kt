package com.android.gaslov.topratedmovies.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

private const val BASE_BIG_POSTER_URL = "https://image.tmdb.org/t/p/w300"

@BindingAdapter("imageUrlEndpoint")
fun loadImage(view: ImageView, posterPath: String?) {
    posterPath?.let {
        Picasso
            .get()
            .load(BASE_BIG_POSTER_URL + posterPath)
            .into(view)
    }
}