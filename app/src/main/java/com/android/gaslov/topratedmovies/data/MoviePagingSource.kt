package com.android.gaslov.topratedmovies.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.gaslov.topratedmovies.domain.GetMovieListUseCase
import com.android.gaslov.topratedmovies.domain.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource : PagingSource<Int, Movie>() {

    private val getMovieListUseCase = GetMovieListUseCase()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val pageNumber = params.key ?: 1
            val response = getMovieListUseCase(pageNumber)

            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null

            return LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error<Int, Movie>(e)
        } catch (e: HttpException) {
            return LoadResult.Error<Int, Movie>(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}