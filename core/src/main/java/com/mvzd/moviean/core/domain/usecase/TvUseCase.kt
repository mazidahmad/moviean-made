package com.mvzd.moviean.core.domain.usecase

import com.mvzd.moviean.core.domain.model.Tv
import com.mvzd.moviean.core.data.source.remote.Resource
import kotlinx.coroutines.flow.Flow

interface TvUseCase {
    fun getAllTvs(): Flow<Resource<List<Tv>>>
    fun getTvDetail(sid: Int): Flow<Resource<Tv>>
    fun getAllFavouriteTvs(): Flow<List<Tv>>
    fun setFavouriteTv(tv: Tv, state: Boolean)
}