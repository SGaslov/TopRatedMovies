package com.android.gaslov.topratedmovies.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailDto(
    @SerializedName("id") val movieId: Int,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: List<GenreDto>,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryDto>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val rating: Double
)
