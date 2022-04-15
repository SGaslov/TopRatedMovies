package com.android.gaslov.topratedmovies.data.network.model

import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("id") val genreId: Int,
    @SerializedName("name") val genreName: String
)
