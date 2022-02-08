package com.mvzd.moviean.di

import com.mvzd.moviean.core.domain.usecase.MovieUseCase
import com.mvzd.moviean.core.domain.usecase.TvUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavouriteModuleDependencies {

    fun movieUseCase() : MovieUseCase
    fun tvUseCase() : TvUseCase
}