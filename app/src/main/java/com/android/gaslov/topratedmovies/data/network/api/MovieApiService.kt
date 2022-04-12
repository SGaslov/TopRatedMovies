package com.android.gaslov.topratedmovies.data.network.api

import com.android.gaslov.topratedmovies.BuildConfig
import com.android.gaslov.topratedmovies.data.network.model.GenreListDto
import com.android.gaslov.topratedmovies.data.network.model.MovieDetailDto
import com.android.gaslov.topratedmovies.data.network.model.MovieListContainerDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = HIDDEN_API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE,
        @Query(QUERY_PARAM_PAGE) page: Int
    ): MovieListContainerDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = HIDDEN_API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE
    ): MovieDetailDto

    @GET("genre/movie/list")
    suspend fun getAllGenreList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = HIDDEN_API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE
    ): GenreListDto

    companion object {

        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_PAGE = "page"

        private const val LANGUAGE = "en-US"
        private const val HIDDEN_API_KEY: String = BuildConfig.API_KEY
    }
}