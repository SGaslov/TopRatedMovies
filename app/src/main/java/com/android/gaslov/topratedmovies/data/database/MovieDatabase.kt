package com.android.gaslov.topratedmovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.gaslov.topratedmovies.domain.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    //TODO: MovieDatabase must be a singleton
}