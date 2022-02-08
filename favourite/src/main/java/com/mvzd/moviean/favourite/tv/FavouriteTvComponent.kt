package com.mvzd.moviean.favourite.tv

import android.content.Context
import com.mvzd.moviean.di.FavouriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavouriteModuleDependencies::class])
interface FavouriteTvComponent {
    fun inject(fragment: TvListFavouriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favouriteModuleDependencies: FavouriteModuleDependencies): Builder
        fun build(): FavouriteTvComponent
    }
}