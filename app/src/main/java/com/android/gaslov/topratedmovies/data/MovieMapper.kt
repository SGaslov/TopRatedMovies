package com.android.gaslov.topratedmovies.data

import com.android.gaslov.topratedmovies.data.network.model.GenreDto
import com.android.gaslov.topratedmovies.data.network.model.MovieDetailDto
import com.android.gaslov.topratedmovies.data.network.model.MovieDto
import com.android.gaslov.topratedmovies.data.network.model.ProductionCountryDto
import com.android.gaslov.topratedmovies.domain.Movie

class MovieMapper {

    // TODO: implement string resources

    fun movieDetailDtoToMovie(movieDetailDto: MovieDetailDto): Movie {
        return with(movieDetailDto) {
            Movie(
                entityId = 0,
                movieId = movieId,
                budget = "Budget: ${budget.toString()}",
                genres = genresListToGenresString(genres),
                overview = overview,
                page = 0,
                popularity = popularity.toString(),
                posterPath = posterPath,
                productionCountries = countriesListToCountriesString(productionCountries),
                title = title,
                rating = "TMDB rating: ${rating.toString()}"
            )
        }
    }

    fun movieDtoToMovie(movieDto: MovieDto, allGenresList: List<GenreDto>, page: Int): Movie {
        return with(movieDto) {
            Movie(
                entityId = 0,
                movieId = movieId,
                budget = "",
                genres = genreIdsToGenresString(genreIds, allGenresList),
                overview = overview,
                page = page,
                popularity = popularity.toString(),
                posterPath = posterPath,
                productionCountries = "",
                title = title,
                rating = "Rating: ${rating.toString()}"
            )
        }
    }

    private fun genresListToGenresString(genresList: List<GenreDto>): String {
        var genresString = "Genres: "

        val listOfStrings = genresList.map { it.genreName }
        for (i in listOfStrings.indices) {
            genresString += if (i < (listOfStrings.size - 1)) {
                "${listOfStrings[i]}, "
            } else {
                listOfStrings[i]
            }
        }
        return genresString
    }

    private fun genreIdsToGenresString(
        genreIds: List<Int>,
        allGenresList: List<GenreDto>
    ): String {
        var genresString = "Genres: "

        for (i in genreIds.indices) {
            for (genreDto in allGenresList) {
                if (genreIds[i] == genreDto.genreId) {
                    genresString += if (i < (genreIds.size - 1)) {
                        "${genreDto.genreName}, "
                    } else {
                        genreDto.genreName
                    }
                    break
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