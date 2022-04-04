package com.android.gaslov.topratedmovies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.gaslov.topratedmovies.domain.Movie
import com.android.gaslov.topratedmovies.domain.MovieRepository

class MovieRepositoryImpl : MovieRepository {

    private val apiService = MovieApiFactory.service

    private val mapper = MovieMapper()

    override fun getMovie(movieId: Int): LiveData<Movie> {
        val movieDetailDto = apiService.getMovieDetail(movieId.toString())

        val movie = mapper.movieDetailDtoToMovie(movieDetailDto)

        return MutableLiveData<Movie>().apply {
            value = movie
        }
    }

    override fun getMovieList(): LiveData<List<Movie>> {
        val movieListContainerDto = apiService.getTopRatedMovies()
        val movieListDto = movieListContainerDto.movieList

        val movieList = movieListDto.map { mapper.movieDtoToMovie(it) }

            return MutableLiveData<List<Movie>>().apply {
                value = movieList
            }
    }
}