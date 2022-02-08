package com.mvzd.moviean.favourite.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvzd.moviean.databinding.FragmentMovieListFavouriteBinding
import com.mvzd.moviean.core.domain.model.Movie
import com.mvzd.moviean.detail.movie.DetailMovieActivity
import com.mvzd.moviean.di.FavouriteModuleDependencies
import com.mvzd.moviean.favourite.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class MovieListFavouriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentMovieListFavouriteBinding
    private val movieListViewModel: MovieListFavouriteViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerFavouriteMovieComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavouriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        binding = FragmentMovieListFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMoviesFav.visibility = View.GONE
        binding.loadingFavMovie.visibility = View.VISIBLE
        binding.noFavMovie.visibility = View.GONE

        movieListViewModel.listFavouriteMovie.observe(viewLifecycleOwner, { movies ->
            if (movies != null && movies.isNotEmpty()){
                showRecyclerList(view.context, movies)
            }else {
                binding.rvMoviesFav.visibility = View.GONE
                binding.loadingFavMovie.visibility = View.GONE
                binding.noFavMovie.visibility = View.VISIBLE
            }
        })
    }

    private fun showRecyclerList(context: Context, movies: List<Movie>) {
        binding.rvMoviesFav.visibility = View.VISIBLE
        binding.loadingFavMovie.visibility = View.GONE
        binding.noFavMovie.visibility = View.GONE
        binding.rvMoviesFav.layoutManager = LinearLayoutManager(context)
        val movieListAdapter = MovieListFavouriteAdapter(movies)
        binding.rvMoviesFav.adapter = movieListAdapter
        movieListAdapter.setOnItemClickCallback(object :
            MovieListFavouriteAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(activity?.application, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.id)
                startActivity(intent)
            }
        })
    }

    companion object
}