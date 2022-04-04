package com.android.gaslov.topratedmovies.data

import com.android.gaslov.topratedmovies.BuildConfig
import com.android.gaslov.topratedmovies.data.model.MovieDetailDto
import com.android.gaslov.topratedmovies.data.model.MovieListContainerDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = HIDDEN_API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE,
        @Query(QUERY_PARAM_PAGE) page: Int = 1
    ): MovieListContainerDto

    @GET("/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = HIDDEN_API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE
    ): MovieDetailDto

    companion object {

        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_PAGE = "page"

        private const val LANGUAGE = "en-US"
        private const val HIDDEN_API_KEY: String = BuildConfig.API_KEY
    }
}