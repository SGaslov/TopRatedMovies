package com.android.gaslov.topratedmovies.data

import com.android.gaslov.topratedmovies.data.network.api.MovieApiFactory
import com.android.gaslov.topratedmovies.domain.Movie
import com.android.gaslov.topratedmovies.domain.MovieRepository

class MovieRepositoryImpl : MovieRepository {

    private val apiService = MovieApiFactory.service

    private val mapper = MovieMapper()

    override suspend fun getMovieDetail(movieId: Int): Movie {
        val movieDetailDto = apiService.getMovieDetail(movieId.toString())
        return mapper.movieDetailDtoToMovie(movieDetailDto)
    }

    override suspend fun getMovieList(page: Int): List<Movie> {
        val movieListContainerDto = apiService.getTopRatedMovies(page = page)
        val movieListDto = movieListContainerDto.movieList

        val allGenresList =apiService.getAllGenreList().genreList

        return movieListDto.map { mapper.movieDtoToMovie(it, allGenresList, page) }
    }

    override suspend fun getTotalPages(): Int {
        val movieListContainerDto = apiService.getTopRatedMovies(page = 1)
        return movieListContainerDto.totalPages
    }
}