package com.mvzd.moviean.core.domain.repository

import com.mvzd.moviean.core.data.source.remote.Resource
import com.mvzd.moviean.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface ITvRepository {
    fun getAllTvs(): Flow<Resource<List<Tv>>>
    fun getDetailTv(sid: Int): Flow<Resource<Tv>>
    fun getFavouriteTvs(): Flow<List<Tv>>
    fun setFavouriteTvs(tv: Tv, state: Boolean)
}