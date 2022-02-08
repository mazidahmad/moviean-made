package com.mvzd.moviean.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvzd.moviean.core.domain.usecase.MovieUseCase
import com.mvzd.moviean.core.domain.usecase.TvUseCase
import com.mvzd.moviean.favourite.movie.MovieListFavouriteViewModel
import com.mvzd.moviean.favourite.tv.TvListFavouriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase, private val tvUseCase: TvUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieListFavouriteViewModel::class.java) -> {
                MovieListFavouriteViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(TvListFavouriteViewModel::class.java) -> {
                TvListFavouriteViewModel(tvUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}