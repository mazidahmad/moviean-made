package com.mvzd.moviean.core.di

import android.content.Context
import androidx.room.Room
import com.mvzd.moviean.core.data.source.local.room.MovieDao
import com.mvzd.moviean.core.data.source.local.room.MovieanDatabase
import com.mvzd.moviean.core.data.source.local.room.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieanDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("moviean".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context.applicationContext,
            MovieanDatabase::class.java,
            "Moviean.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory).build()
    }


    @Provides
    fun provideMovieDao(database: MovieanDatabase): MovieDao = database.movieDao()

    @Provides
    fun provideTvDao(database: MovieanDatabase): TvDao = database.tvDao()
}