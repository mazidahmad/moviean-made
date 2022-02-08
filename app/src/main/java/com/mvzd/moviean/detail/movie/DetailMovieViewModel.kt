package com.mvzd.moviean.detail.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mvzd.moviean.core.domain.model.Movie
import com.mvzd.moviean.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun movieDetail(id: Int) = movieUseCase.getMovieDetail(id).asLiveData()
    fun setFavouriteMovie(movie: Movie, state: Boolean) = movieUseCase.setFavouriteMovie(movie, state)
}