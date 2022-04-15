package com.android.gaslov.topratedmovies.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCountryDto(
    @SerializedName("name")
    @Expose
    val countryName: String?
)
