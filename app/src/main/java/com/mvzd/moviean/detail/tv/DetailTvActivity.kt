package com.mvzd.moviean.detail.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.mvzd.moviean.R
import com.mvzd.moviean.databinding.ActivityDetailTvBinding
import com.mvzd.moviean.core.domain.model.Tv
import com.mvzd.moviean.core.utils.EspressoIdlingResource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvBinding
    private val detailViewModel: DetailTvViewModel by viewModels()
    private var currentTv: Tv? = null
    private var itemFav: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvId = intent.getIntExtra(EXTRA_TV, 0)
        detailViewModel.tvDetail(tvId).observe(this, { tvData ->
            if (tvData.data != null) {
                currentTv = tvData.data
                fetchTvDetail(tvData.data!!)
                if (currentTv != null && currentTv!!.isFavourite == true) itemFav?.setIcon(R.drawable.ic_favorite_white) else itemFav?.setIcon(
                    R.drawable.ic_not_favorite_white
                )
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        itemFav = menu.findItem(R.id.action_favourite)
        if (currentTv != null && currentTv!!.isFavourite == true) itemFav?.setIcon(R.drawable.ic_favorite_white) else itemFav?.setIcon(
            R.drawable.ic_not_favorite_white
        )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favourite) {
            val state = if (currentTv != null && currentTv!!.isFavourite != null) !currentTv!!.isFavourite!! else true
            detailViewModel.setFavouriteTv(currentTv!!, state)

            if (state) item.setIcon(R.drawable.ic_favorite_white) else item.setIcon(R.drawable.ic_not_favorite_white)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchTvDetail(tv: Tv) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${tv.posterPath}")
            .resize(300, 450)
            .centerCrop()
            .into(binding.imagePoster)
        binding.titleTv.text = tv.name
        binding.descriptionTvDetail.text = tv.overview
        binding.rateTvValue.text = tv.voteAverage.toString()
        binding.popularityTvValue.text = tv.popularity.toString()
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    companion object {
        const val EXTRA_TV = "EXTRA_TV"
    }
}