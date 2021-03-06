package com.android.gaslov.topratedmovies.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.gaslov.topratedmovies.R
import com.android.gaslov.topratedmovies.databinding.FragmentMovieListBinding
import com.android.gaslov.topratedmovies.di.ApplicationGraph
import com.android.gaslov.topratedmovies.presentation.adapters.MovieAdapter
import com.android.gaslov.topratedmovies.presentation.adapters.MovieViewHolder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
    }

    private val appGraph: ApplicationGraph by lazy {
        (requireActivity().application as MyApplication).appGraph
    }

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    override fun onAttach(context: Context) {
        appGraph.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleOnBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        setRecyclerViewOnItemClickListener()

         viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pager.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.movieListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = MovieAdapter()
        recyclerView.adapter = adapter
    }

    private fun setRecyclerViewOnItemClickListener() {
        MovieViewHolder.onClickListener = { movieId ->
            val movieDetailFragment = MovieDetailFragment.newInstance(movieId)

            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragmentContainer, movieDetailFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
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

    //TODO: add refresh button
}