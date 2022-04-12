package com.android.gaslov.topratedmovies.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.gaslov.topratedmovies.domain.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovieList(): LiveData<List<Movie>>

    @Insert
    fun insertMovieList(movieList: List<Movie>)
}