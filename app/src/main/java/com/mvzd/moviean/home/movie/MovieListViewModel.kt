package com.mvzd.moviean.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mvzd.moviean.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val listMovie = movieUseCase.getAllMovies().asLiveData()
}