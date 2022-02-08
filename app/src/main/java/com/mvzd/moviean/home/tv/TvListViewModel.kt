package com.mvzd.moviean.home.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mvzd.moviean.core.domain.usecase.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(tvUseCase: TvUseCase) : ViewModel() {
    val listTv = tvUseCase.getAllTvs().asLiveData()
}