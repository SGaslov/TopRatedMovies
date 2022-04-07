package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import com.android.gaslov.topratedmovies.R
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

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
        val posterImageView: ImageView = view.findViewById(R.id.detailPosterImageView)

        viewModel.movieDetail.observe(viewLifecycleOwner) { wrapper ->
            wrapper.getContentIfNotHandled()?.let { movie ->
                titleTextView.text = movie.title
                genresTextView.text = movie.genres
                productionCountries.text = movie.productionCountries
                ratingTextView.text = movie.rating
                overviewTextView.text = movie.overview

                Picasso
                    .get()
                    .load(POSTER_BASE_URL + movie.posterPath)
                    .into(posterImageView)
            }
        }
    }

    private fun setNavBackButtonOnClickListener(view: View) {
        view.findViewById<Toolbar>(R.id.detailFragmentToolbar)
            .setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object {

        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w300"
    }
}