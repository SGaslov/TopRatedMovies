package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.domain.Movie
import com.android.gaslov.topratedmovies.presentation.adapters.MovieAdapter

class MovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter

    private val viewModel: MovieViewModel by activityViewModels()

    private val movieList = mutableListOf<Movie>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleOnBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.movieListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        viewModel.getMovieList().observe(viewLifecycleOwner) {
            adapter = MovieAdapter(it)
            adapter.onClickListener = {
                val fragment = MovieDetailFragment.newInstance(
                    "Title",
                    "Comedy",
                    "7.8",
                    "Great film!"
                )
                requireActivity().supportFragmentManager.commit {
                    replace(R.id.fragmentContainer, fragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
            recyclerView.adapter = adapter
        }
    }

    private fun handleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

//    private fun fillMovieList() {
//        movieList.apply {
//            for (i in 1..20) {
//                add(
//                    Movie(
//                        i,
//                        listOf("Animation", "Family"),
//                        "Overview: Nemo, an adventurous young clownfish, is unexpectedly taken" +
//                                " from his Great Barrier Reef home to a dentist's office aquarium. It's" +
//                                " up to his worrisome father Marlin and a friendly but forgetful fish" +
//                                " Dory to bring Nemo home -- meeting vegetarian sharks, surfer dude" +
//                                " turtles, hypnotic jellyfish, hungry seagulls, and more along the way.",
//                        5500.0,
//                        "",
//                        listOf("USA"),
//                        "Finding Nemo Finding Nemo Finding Nemo Finding Nemo",
//                        7.8
//                    )
//                )
//            }
//        }
//    }
}