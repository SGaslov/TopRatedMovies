package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.android.gaslov.topratedmovies.R

private const val ARG_TITLE = "title"
private const val ARG_GENRES = "genres"
private const val ARG_RATING = "rating"
private const val ARG_OVERVIEW = "overview"

class MovieDetailFragment : Fragment() {

    private var title: String? = null
    private var genres: String? = null
    private var rating: String? = null
    private var overview: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            genres = it.getString(ARG_GENRES)
            rating = it.getString(ARG_RATING)
            overview = it.getString(ARG_OVERVIEW)
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
        val ratingTextView: TextView = view.findViewById(R.id.detailRatingTextView)
        val overviewTextView: TextView = view.findViewById(R.id.overviewTextView)

        titleTextView.text = title
        genresTextView.text = genres
        ratingTextView.text = rating
        overviewTextView.text = overview
    }

    private fun setNavBackButtonOnClickListener(view: View) {
        view.findViewById<Toolbar>(R.id.detailFragmentToolbar)
            .setNavigationOnClickListener {
            requireActivity().supportFragmentManager?.popBackStack()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(title: String, genres: String, rating: String, overview: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_GENRES, genres)
                    putString(ARG_RATING, rating)
                    putString(ARG_OVERVIEW, overview)
                }
            }
    }
}