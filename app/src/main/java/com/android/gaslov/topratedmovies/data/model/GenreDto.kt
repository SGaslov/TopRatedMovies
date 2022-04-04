package com.android.gaslov.topratedmovies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("name")
    @Expose
    val genre: String
)
