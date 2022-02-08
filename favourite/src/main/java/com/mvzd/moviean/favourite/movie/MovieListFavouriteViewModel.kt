package com.mvzd.moviean.favourite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mvzd.moviean.core.domain.usecase.MovieUseCase

class MovieListFavouriteViewModel (movieUseCase: MovieUseCase) : ViewModel() {
    val listFavouriteMovie = movieUseCase.getAllFavouriteMovies().asLiveData()
}