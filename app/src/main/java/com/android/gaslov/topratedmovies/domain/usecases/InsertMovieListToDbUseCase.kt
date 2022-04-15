package com.android.gaslov.topratedmovies.domain.usecases

import com.android.gaslov.topratedmovies.domain.Movie
import com.android.gaslov.topratedmovies.domain.MovieRepository
import javax.inject.Inject

class InsertMovieListToDbUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(movieList: List<Movie>) = repository.insertMovieListToDb(movieList)
}