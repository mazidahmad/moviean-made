package com.mvzd.moviean.core.data.source.local.room

import androidx.room.*
import com.mvzd.moviean.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isFavourite = 1")
    fun getFavouriteMovie(): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}