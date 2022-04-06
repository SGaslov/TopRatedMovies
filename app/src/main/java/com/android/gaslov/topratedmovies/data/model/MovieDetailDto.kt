package com.android.gaslov.topratedmovies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetailDto(
    @SerializedName("budget")
    @Expose
    val budget: Int?,
    @SerializedName("genres")
    @Expose
    val genres: List<GenreDto>,
    @SerializedName("overview")
    @Expose
    val overview: String?,
    @SerializedName("popularity")
    @Expose
    val popularity: Double?,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,
    @SerializedName("production_countries")
    @Expose
    val productionCountries: List<ProductionCountryDto>?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("vote_average")
    @Expose
    val rating: Double?
)
