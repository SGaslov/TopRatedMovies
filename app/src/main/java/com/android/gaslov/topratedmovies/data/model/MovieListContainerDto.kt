package com.android.gaslov.topratedmovies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieListContainerDto(
    @SerializedName("page")
    @Expose
    val page: Int?,
    @SerializedName("result")
    @Expose
    val movieList: List<MovieDto>?
)
