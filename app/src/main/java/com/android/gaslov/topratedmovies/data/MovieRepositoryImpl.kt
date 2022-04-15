package com.android.gaslov.topratedmovies.data

import android.app.Application
import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.android.gaslov.topratedmovies.data.database.MovieDatabase
import com.android.gaslov.topratedmovies.data.network.api.MovieApiFactory
import com.android.gaslov.topratedmovies.domain.Movie
import com.android.gaslov.topratedmovies.domain.MovieRepository

class MovieRepositoryImpl(application: Application) : MovieRepository {

    private val apiService = MovieApiFactory.service
    private val mapper = MovieMapper()
    private val db = MovieDatabase.getInstance(application)
    private val movieDao = db.movieDao()

    override suspend fun getMovieDetailFromWeb(movieId: Int): Movie {
        val movieDetailDto = apiService.getMovieDetail(movieId.toString())
        return mapper.movieDetailDtoToMovie(movieDetailDto)
    }

    override suspend fun getMovieListFromWeb(page: Int): List<Movie> {
        val movieListContainerDto = apiService.getTopRatedMovies(page = page)
        val movieListDto = movieListContainerDto.movieList

        val allGenresList =apiService.getAllGenreList().genreList

        return movieListDto.map { mapper.movieDtoToMovie(it, allGenresList, page) }
    }

    override suspend fun getTotalPagesFromWeb(): Int {
        val movieListContainerDto = apiService.getTopRatedMovies(page = 1)
        return movieListContainerDto.totalPages
    }

    override fun getMovieListFromDb(): PagingSource<Int, Movie> {
        return movieDao.getMovieList()
    }

    override suspend fun insertMovieListToDb(movieList: List<Movie>) {
        movieDao.insertMovieList(movieList)
    }

    override suspend fun refreshMovieListInDb(movieList: List<Movie>) {
        db.withTransaction {
            movieDao.clearAll()
            movieDao.insertMovieList(movieList)
        }

    }
}