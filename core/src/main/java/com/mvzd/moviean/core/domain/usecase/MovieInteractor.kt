package com.mvzd.moviean.core.domain.usecase

import com.mvzd.moviean.core.domain.model.Movie
import com.mvzd.moviean.core.domain.repository.IMovieRepository
import com.mvzd.moviean.core.data.source.remote.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getMovieDetail(sid: Int): Flow<Resource<Movie>> = movieRepository.getMovieDetail(sid)

    override fun getAllFavouriteMovies(): Flow<List<Movie>> = movieRepository.getAllFavouriteMovies()

    override fun setFavouriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavouriteMovie(movie, state)
}