package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.domain.Movie
import com.android.gaslov.topratedmovies.presentation.adapters.MovieAdapter

class MovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter

    private val movieList = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        fillMovieList()

        setOnClickListener()

        setUpRecyclerView(view)

        return view
    }

    private fun setOnClickListener() {
        adapter = MovieAdapter(movieList).apply {
            onClickListener = {

                val fragment = MovieDetailFragment.newInstance(
                    movieList[0].title,
                    movieList[0].genres,
                    movieList[0].rating,
                    movieList[0].overview
                )

                requireActivity().supportFragmentManager.commit {
                    replace(R.id.fragmentContainer, fragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }
    }

    private fun setUpRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.movieListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter
    }


    private fun fillMovieList() {
        movieList.apply {
            for (i in 1..20) {
                add(
                    Movie(
                        i,
                        "Finding Nemo Finding Nemo Finding Nemo Finding Nemo",
                        "Genres: Animation, Family",
                        "Popularity: 5500",
                        "Rating: 7.8",
                        "Overview: Nemo, an adventurous young clownfish, is unexpectedly taken" +
                                " from his Great Barrier Reef home to a dentist's office aquarium. It's" +
                                " up to his worrisome father Marlin and a friendly but forgetful fish" +
                                " Dory to bring Nemo home -- meeting vegetarian sharks, surfer dude" +
                                " turtles, hypnotic jellyfish, hungry seagulls, and more along the way."
                    )
                )
            }
        }
    }
}