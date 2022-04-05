package com.android.gaslov.topratedmovies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    @Expose
    val movieId: Int?,
    @SerializedName("genres")
    @Expose
    val genres: List<GenreDto>?,
    @SerializedName("popularity")
    @Expose
    val popularity: Double?,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("vote_average")
    @Expose
    val rating: Double?
)
