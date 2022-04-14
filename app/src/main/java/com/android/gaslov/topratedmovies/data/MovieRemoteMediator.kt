package com.android.gaslov.topratedmovies.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.android.gaslov.topratedmovies.data.database.MovieDatabase
import com.android.gaslov.topratedmovies.domain.GetMovieListUseCase
import com.android.gaslov.topratedmovies.domain.GetTotalPagesUseCase
import com.android.gaslov.topratedmovies.domain.Movie
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val db: MovieDatabase,
    private val getTotalPagesUseCase: GetTotalPagesUseCase,
    private val getMovieListUseCase: GetMovieListUseCase
) : RemoteMediator<Int, Movie>() {

//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {
        return try {
            val totalPages = getTotalPagesUseCase()

            Log.d("MyDebug", "Total pages: $totalPages")
            Log.d("MyDebug", loadType.name)

            val currentPage = state.lastItemOrNull()?.page ?: 0

            val nextPage = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    if (currentPage == totalPages) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    currentPage + 1
                }
            }

            Log.d("MyDebug", "Next page: $nextPage")

            val response = getMovieListUseCase(nextPage)
            var titles = ""
            for (movie: Movie in response) {
                titles = titles + movie.title + " | "
            }

            Log.d("MyDebug", titles)

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.movieDao().clearAll()
                }
                db.movieDao().insertMovieList(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = nextPage == totalPages
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}