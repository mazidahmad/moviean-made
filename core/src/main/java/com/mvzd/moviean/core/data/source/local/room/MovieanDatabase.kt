package com.mvzd.moviean.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvzd.moviean.core.data.source.local.entity.MovieEntity
import com.mvzd.moviean.core.data.source.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class MovieanDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}