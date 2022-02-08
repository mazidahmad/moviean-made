package com.mvzd.moviean.core.data.source.local

import com.mvzd.moviean.core.data.source.local.entity.MovieEntity
import com.mvzd.moviean.core.data.source.local.entity.TvEntity
import com.mvzd.moviean.core.data.source.local.room.MovieDao
import com.mvzd.moviean.core.data.source.local.room.TvDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mMovieDao: MovieDao, private val mTvDao: TvDao) {

    fun getMovies(): Flow<List<MovieEntity>> = mMovieDao.getMovies()

    fun getFavouriteMovies(): Flow<List<MovieEntity>> = mMovieDao.getFavouriteMovie()

    fun setFavouriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavourite = newState
        mMovieDao.updateMovie(movie)
    }

    fun getMovieDetail(id: Int): Flow<MovieEntity> = mMovieDao.getMovieById(id)

    suspend fun addMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun getTvs(): Flow<List<TvEntity>> = mTvDao.getTvs()

    fun getFavouriteTvs(): Flow<List<TvEntity>> = mTvDao.getFavouriteTv()

    fun setFavouriteTv(tv: TvEntity, newState: Boolean) {
        tv.isFavourite = newState
        mTvDao.updateTv(tv)
    }

    fun getTvDetail(id: Int): Flow<TvEntity> = mTvDao.getTvById(id)

    suspend fun addTvs(tvs: List<TvEntity>) = mTvDao.insertTvs(tvs)
}