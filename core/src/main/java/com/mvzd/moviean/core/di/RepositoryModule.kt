package com.mvzd.moviean.core.di

import com.mvzd.moviean.core.data.MovieRepository
import com.mvzd.moviean.core.data.TvRepository
import com.mvzd.moviean.core.domain.repository.IMovieRepository
import com.mvzd.moviean.core.domain.repository.ITvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieRepository(movieRepository: MovieRepository): IMovieRepository
    @Binds
    abstract fun provideTvRepository(tvRepository: TvRepository): ITvRepository
}