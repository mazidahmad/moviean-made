package com.mvzd.moviean.core.domain.usecase

import com.mvzd.moviean.core.domain.model.Tv
import com.mvzd.moviean.core.domain.repository.ITvRepository
import com.mvzd.moviean.core.data.source.remote.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvInteractor @Inject constructor(private val tvRepository: ITvRepository) : TvUseCase {
    override fun getAllTvs(): Flow<Resource<List<Tv>>> = tvRepository.getAllTvs()

    override fun getTvDetail(sid: Int): Flow<Resource<Tv>> = tvRepository.getDetailTv(sid)

    override fun getAllFavouriteTvs(): Flow<List<Tv>> = tvRepository.getFavouriteTvs()

    override fun setFavouriteTv(tv: Tv, state: Boolean) =
        tvRepository.setFavouriteTvs(tv, state)
}