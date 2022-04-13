package com.android.gaslov.topratedmovies.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.gaslov.topratedmovies.domain.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private var db: MovieDatabase? = null
        private const val DB_NAME = "movie_database"

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }
}