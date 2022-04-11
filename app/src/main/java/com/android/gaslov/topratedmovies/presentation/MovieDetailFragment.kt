package com.android.gaslov.topratedmovies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

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

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        val movieId = requireArguments().getInt(ARG_MOVIE_ID)

        if (savedInstanceState == null) {
            viewModel.loadMovieDetailInfo(movieId)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setNavBackButtonOnClickListener(view)
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