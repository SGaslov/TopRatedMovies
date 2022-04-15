package com.android.gaslov.topratedmovies.presentation

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.android.gaslov.topratedmovies.domain.*
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val getTotalPagesUseCase: GetTotalPagesUseCase,
    private val getMovieListFromWebUseCase: GetMovieListFromWebUseCase,
    private val insertMovieListToDbUseCase: InsertMovieListToDbUseCase,
    private val refreshMovieListInDbUseCase: RefreshMovieListInDbUseCase
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
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}