package com.mvzd.moviean.core.data.source.local.room

import androidx.room.*
import com.mvzd.moviean.core.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {

    @Query("SELECT * FROM tvs")
    fun getTvs(): Flow<List<TvEntity>>

    @Query("SELECT * FROM tvs WHERE isFavourite = 1")
    fun getFavouriteTv(): Flow<List<TvEntity>>

    @Transaction
    @Query("SELECT * FROM tvs WHERE id = :id")
    fun getTvById(id: Int): Flow<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvs(tvs: List<TvEntity>)

    @Update
    fun updateTvs(tvs: List<TvEntity>)

    @Update
    fun updateTv(tv: TvEntity)
}