package com.android.gaslov.topratedmovies.data.model

import com.google.gson.annotations.SerializedName

data class GenreListDto(
    @SerializedName("genres") val genreList: List<GenreDto>
)