package com.android.gaslov.topratedmovies.data

import com.android.gaslov.topratedmovies.data.model.MovieDetailDto
import com.android.gaslov.topratedmovies.data.model.MovieDto
import com.android.gaslov.topratedmovies.domain.Movie

class MovieMapper {

    fun movieDetailDtoToMovie(movieDetailDto: MovieDetailDto): Movie {
        return with(movieDetailDto) {
            Movie(
                budget = budget,
                genres = genres.map { it.genre },
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                productionCountries = productionCountries.map { it.countryName },
                title = title,
                rating = rating
            )
        }
    }

    fun movieDtoToMovie(movieDto: MovieDto): Movie {
        return with(movieDto) {
            Movie(
                genres = genres.map { it.genre },
                popularity = popularity,
                posterPath = posterPath,
                title = title,
                rating = rating
            )
        }
    }
}