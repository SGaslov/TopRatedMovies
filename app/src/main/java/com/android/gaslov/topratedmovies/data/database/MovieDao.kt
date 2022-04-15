package com.android.gaslov.topratedmovies.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.gaslov.topratedmovies.domain.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovieList(): PagingSource<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}