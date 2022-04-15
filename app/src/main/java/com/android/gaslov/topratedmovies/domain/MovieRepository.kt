package com.android.gaslov.topratedmovies.domain

import androidx.paging.PagingSource

interface MovieRepository {

    suspend fun getMovieDetailFromWeb(movieId: Int): Movie

    suspend fun getMovieListFromWeb(page: Int): List<Movie>

    suspend fun getTotalPagesFromWeb(): Int

    fun getMovieListFromDb(): PagingSource<Int, Movie>

    suspend fun insertMovieListToDb(movieList: List<Movie>)

    suspend fun refreshMovieListInDb(movieList: List<Movie>)
}