package com.android.gaslov.topratedmovies.presentation

import android.app.Application
import android.widget.Toast
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.android.gaslov.topratedmovies.domain.*
import com.android.gaslov.topratedmovies.domain.usecases.GetMovieListFromWebUseCase
import com.android.gaslov.topratedmovies.domain.usecases.GetTotalPagesUseCase
import com.android.gaslov.topratedmovies.domain.usecases.InsertMovieListToDbUseCase
import com.android.gaslov.topratedmovies.domain.usecases.RefreshMovieListInDbUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val getTotalPagesUseCase: GetTotalPagesUseCase,
    private val getMovieListFromWebUseCase: GetMovieListFromWebUseCase,
    private val insertMovieListToDbUseCase: InsertMovieListToDbUseCase,
    private val refreshMovieListInDbUseCase: RefreshMovieListInDbUseCase,
    private val application: Application
) : RemoteMediator<Int, Movie>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {
        return try {
            val totalPages = getTotalPagesUseCase()

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

            val response = getMovieListFromWebUseCase(nextPage)
            var titles = ""
            for (movie: Movie in response) {
                titles = titles + movie.title + " | "
            }

            if (loadType == LoadType.REFRESH) {
                refreshMovieListInDbUseCase(response)
            } else {
                insertMovieListToDbUseCase(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = nextPage == totalPages
            )
            // TODO: add error processing
        } catch (error: IOException) {
            MediatorResult.Error(error)
        } catch (error: HttpException) {
            Toast.makeText(
                application,
                "Server connection problem ${error.code()}",
                Toast.LENGTH_LONG
            ).show()
            MediatorResult.Error(error)
        }
    }
}