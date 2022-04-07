package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.presentation.adapters.MovieAdapter

class MovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter

    private val viewModel: MovieViewModel by activityViewModels()

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

        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            adapter = MovieAdapter(movieList)
            adapter.onClickListener = { movieId ->
                val movieDetailFragment = MovieDetailFragment.newInstance(movieId)

                requireActivity().supportFragmentManager.commit {
                    replace(R.id.fragmentContainer, movieDetailFragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())
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
}