package com.android.gaslov.topratedmovies.data

import com.android.gaslov.topratedmovies.data.model.GenreDto
import com.android.gaslov.topratedmovies.data.model.MovieDetailDto
import com.android.gaslov.topratedmovies.data.model.MovieDto
import com.android.gaslov.topratedmovies.data.model.ProductionCountryDto
import com.android.gaslov.topratedmovies.domain.Movie

class MovieMapper {

    // TODO: implement string resources

    fun movieDetailDtoToMovie(movieDetailDto: MovieDetailDto): Movie {
        return with(movieDetailDto) {
            Movie(
                budget = "Budget: ${budget?.toString()}",
                genres = genresListToGenresString(genres),
                overview = overview ?: "",
                popularity = popularity.toString(),
                posterPath = posterPath ?: "",
                productionCountries = countriesListToCountriesString(productionCountries),
                title = title ?: "",
                rating = rating.toString()
            )
        }
    }

    fun movieDtoToMovie(movieDto: MovieDto): Movie {
        return with(movieDto) {
            Movie(
                budget = "",
                genres = genresListToGenresString(genres),
                overview = "",
                popularity = popularity.toString(),
                posterPath = posterPath ?: "",
                productionCountries = "",
                title = title ?: "",
                rating = rating.toString()
            )
        }
    }

    private fun genresListToGenresString(genresList: List<GenreDto>?): String {
        var genresString = "Genres: "

        val listOfStrings = genresList?.map { it.genre ?: "" }
        listOfStrings?.let {
            for (i in it.indices) {
                genresString += if (i < (it.size - 1)) {
                    "${it[i]}, "
                } else {
                    it[i]
                }
            }
        }
        return genresString
    }

    private fun countriesListToCountriesString(countriesList: List<ProductionCountryDto>?): String {
        var countriesString = "Production: "

        val listOfStrings = countriesList?.map { it.countryName ?: "" }
        listOfStrings?.let {
            for (i in it.indices) {
                countriesString += if (i < (it.size - 1)) {
                    "${it[i]}, "
                } else {
                    it[i]
                }
            }
        }
        return countriesString
    }
}