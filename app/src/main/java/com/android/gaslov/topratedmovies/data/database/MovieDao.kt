package com.android.gaslov.topratedmovies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.gaslov.topratedmovies.domain.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovieList(): List<Movie>

    @Insert
    suspend fun insertMovieList(movieList: List<Movie>)
}