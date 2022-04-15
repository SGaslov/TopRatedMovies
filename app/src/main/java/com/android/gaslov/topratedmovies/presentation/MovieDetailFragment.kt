package com.android.gaslov.topratedmovies.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.databinding.FragmentMovieDetailBinding
import com.android.gaslov.topratedmovies.di.ApplicationGraph
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    @Inject
    lateinit var viewModel: MovieViewModel

    private val appGraph: ApplicationGraph by lazy {
        (requireActivity().application as MyApplication).appGraph
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding!!

    override fun onAttach(context: Context) {
        appGraph.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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