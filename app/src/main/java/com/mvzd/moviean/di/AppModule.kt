package com.mvzd.moviean.di

import com.mvzd.moviean.core.domain.usecase.MovieInteractor
import com.mvzd.moviean.core.domain.usecase.MovieUseCase
import com.mvzd.moviean.core.domain.usecase.TvInteractor
import com.mvzd.moviean.core.domain.usecase.TvUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    @Singleton
    abstract fun provideTvUseCase(tvInteractor: TvInteractor): TvUseCase
}