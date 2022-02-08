package com.mvzd.moviean.favourite.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mvzd.moviean.core.domain.usecase.TvUseCase

class TvListFavouriteViewModel (tvUseCase: TvUseCase) : ViewModel() {
    val listFavouriteTv = tvUseCase.getAllFavouriteTvs().asLiveData()
}