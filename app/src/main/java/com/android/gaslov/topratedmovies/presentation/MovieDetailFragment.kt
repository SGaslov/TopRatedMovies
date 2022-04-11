package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val movieId = requireArguments().getInt(ARG_MOVIE_ID)

        setNavBackButtonOnClickListener(view)

        loadMovieDetailData(savedInstanceState, movieId)
    }

    private fun loadMovieDetailData(savedInstanceState: Bundle?, movieId: Int) {
        if (savedInstanceState == null) {
            viewModel.getMovieDetail(movieId)
        }
    }

    private fun setNavBackButtonOnClickListener(view: View) {
        view.findViewById<Toolbar>(R.id.detailFragmentToolbar)
            .setNavigationOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
    }

    companion object {

        private const val ARG_MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int): Fragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOVIE_ID, movieId)
                }
            }
        }
    }
}