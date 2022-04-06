package com.android.gaslov.topratedmovies.data.model

import com.google.gson.annotations.SerializedName

data class MovieListContainerDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movieList: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
