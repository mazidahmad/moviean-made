package com.mvzd.moviean.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.mvzd.moviean.R
import com.mvzd.moviean.databinding.ActivityDetailMovieBinding
import com.mvzd.moviean.core.domain.model.Movie
import com.mvzd.moviean.core.utils.EspressoIdlingResource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val detailViewModel: DetailMovieViewModel by viewModels()
    private var itemFav: MenuItem? = null

    private var currentMovie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)
        detailViewModel.movieDetail(movieId).observe(this, { movieData ->
            if (movieData.data != null) {
                currentMovie = movieData.data
                fetchMovieDetail(movieData.data!!)
                if (currentMovie != null && currentMovie!!.isFavourite == true
                ) itemFav?.setIcon(R.drawable.ic_favorite_white) else itemFav?.setIcon(R.drawable.ic_not_favorite_white)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        itemFav = menu.findItem(R.id.action_favourite)
        if (currentMovie != null && currentMovie!!.isFavourite == true
        ) itemFav?.setIcon(R.drawable.ic_favorite_white) else itemFav?.setIcon(R.drawable.ic_not_favorite_white)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favourite) {
            val state = if (currentMovie != null && currentMovie!!.isFavourite != null) !currentMovie!!.isFavourite!! else true
            detailViewModel.setFavouriteMovie(currentMovie!!, state)

            if (state) item.setIcon(R.drawable.ic_favorite_white) else item.setIcon(R.drawable.ic_not_favorite_white)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun fetchMovieDetail(movie: Movie) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .resize(300, 450)
            .centerCrop()
            .into(binding.imagePoster)
        binding.titleMovie.text = movie.title
        binding.descriptionMovieDetail.text = movie.overview
        binding.rateMovieValue.text = movie.voteAverage.toString()
        binding.popularityMovieValue.text = movie.popularity.toString()
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}