package com.mvzd.moviean.core.domain.repository

import com.mvzd.moviean.core.domain.model.Movie
import com.mvzd.moviean.core.data.source.remote.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(sid: Int): Flow<Resource<Movie>>
    fun getAllFavouriteMovies(): Flow<List<Movie>>
    fun setFavouriteMovie(movie: Movie, state: Boolean)
}