package com.mvzd.moviean.home.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvzd.moviean.core.data.source.remote.StatusResponse
import com.mvzd.moviean.databinding.FragmentMovieListBinding
import com.mvzd.moviean.detail.movie.DetailMovieActivity
import com.mvzd.moviean.core.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private val movieListViewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListViewModel.listMovie.observe(viewLifecycleOwner, { movies ->
            if (movies.data != null && movies.data!!.isNotEmpty()){
                when(movies.status) {
                    StatusResponse.SUCCESS -> {
                        showRecyclerList(view.context, movies.data!!)
                    }
                }
            }
        })
    }

    private fun showRecyclerList(context: Context, movies: List<Movie>) {
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        val movieListAdapter = MovieListAdapter(movies)
        binding.rvMovies.adapter = movieListAdapter
        movieListAdapter.setOnItemClickCallback(object : MovieListAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(activity?.application, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.id)
                startActivity(intent)
            }
        })
    }

    companion object
}