package com.mvzd.moviean.favourite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvzd.moviean.core.databinding.ItemCardBinding
import com.mvzd.moviean.core.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieListFavouriteAdapter(private val listMovie: List<Movie>) :
    RecyclerView.Adapter<MovieListFavouriteAdapter.MovieViewHolder>() {
    private lateinit var binding: ItemCardBinding
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class MovieViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .resize(200, 300)
                .centerCrop()
                .into(binding.imageMovie)
            binding.titleItem.text = movie.title
            binding.descriptionMovie.text = movie.overview
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(movie)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }
}