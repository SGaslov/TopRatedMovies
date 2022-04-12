package com.android.gaslov.topratedmovies.data.network.model

import com.google.gson.annotations.SerializedName

data class GenreListDto(
    @SerializedName("genres") val genreList: List<GenreDto>
)