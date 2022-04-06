package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import com.android.gaslov.topratedmovies.R

private const val ARG_ID = "movie_id"

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(ARG_ID)
        }
        movieId?.let {
            viewModel.getMovieDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavBackButtonOnClickListener(view)

        val titleTextView: TextView = view.findViewById(R.id.detailTitleTextView)
        val genresTextView: TextView = view.findViewById(R.id.detailGenresTextView)
        val productionCountries: TextView = view.findViewById(R.id.productionTextView)
        val ratingTextView: TextView = view.findViewById(R.id.detailRatingTextView)
        val overviewTextView: TextView = view.findViewById(R.id.overviewTextView)

        viewModel.movieDetail.observe(viewLifecycleOwner) {
            titleTextView.text = it.title
            genresTextView.text = it.genres
            productionCountries.text = it.productionCountries
            ratingTextView.text = it.rating
            overviewTextView.text = it.overview
        }
    }

    private fun setNavBackButtonOnClickListener(view: View) {
        view.findViewById<Toolbar>(R.id.detailFragmentToolbar)
            .setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, movieId)
                }
            }
    }
}