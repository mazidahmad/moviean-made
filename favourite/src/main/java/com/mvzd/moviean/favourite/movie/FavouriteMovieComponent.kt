package com.mvzd.moviean.favourite.movie

import android.content.Context
import com.mvzd.moviean.di.FavouriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavouriteModuleDependencies::class])
interface FavouriteMovieComponent {
    fun inject(fragment: MovieListFavouriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favouriteModuleDependencies: FavouriteModuleDependencies): Builder
        fun build(): FavouriteMovieComponent
    }
}