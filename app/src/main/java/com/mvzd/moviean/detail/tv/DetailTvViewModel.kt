package com.mvzd.moviean.detail.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mvzd.moviean.core.domain.model.Tv
import com.mvzd.moviean.core.domain.usecase.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTvViewModel @Inject constructor(private val tvUseCase: TvUseCase) : ViewModel() {
    fun tvDetail(id: Int) = tvUseCase.getTvDetail(id).asLiveData()
    fun setFavouriteTv(tv: Tv, state: Boolean) { tvUseCase.setFavouriteTv(tv, state) }
}