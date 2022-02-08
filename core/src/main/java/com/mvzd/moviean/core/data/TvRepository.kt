package com.mvzd.moviean.core.data

import com.mvzd.moviean.core.data.source.local.LocalDataSource
import com.mvzd.moviean.core.data.source.local.entity.TvEntity
import com.mvzd.moviean.core.data.source.remote.ApiResponse
import com.mvzd.moviean.core.data.source.remote.NetworkBoundResource
import com.mvzd.moviean.core.data.source.remote.RemoteDataSource
import com.mvzd.moviean.core.data.source.remote.response.tv.TvDetailResponse
import com.mvzd.moviean.core.data.source.remote.response.tv.TvResponse
import com.mvzd.moviean.core.utils.AppExecutors
import com.mvzd.moviean.core.data.source.remote.Resource
import com.mvzd.moviean.core.domain.model.Tv
import com.mvzd.moviean.core.domain.repository.ITvRepository
import com.mvzd.moviean.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    ITvRepository {

    override fun getAllTvs(): Flow<Resource<List<Tv>>> =
        object : NetworkBoundResource<List<Tv>, List<TvResponse>>() {
            public override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSource.getTvs().map {
                    DataMapper.mapTvEntitiesToDomain(it)
                }
            }

            override suspend fun shouldFetch(data: List<Tv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> {
                return remoteDataSource.getAllTvs()
            }

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val tvList = ArrayList<TvEntity>()
                for (response in data) {
                    val tv = TvEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        originalLanguage = response.originalLanguage,
                        posterPath = response.posterPath,
                        voteCount = response.voteCount,
                        voteAverage = response.voteAverage,
                        popularity = response.popularity,
                        overview = response.overview,
                        firstAirDate = response.firstAirDate,
                        originalName = response.originalName,
                        name = response.name
                    )
                    tvList.add(tv)
                }
                localDataSource.addTvs(tvList)
            }
        }.asFlow()


    override fun getDetailTv(sid: Int): Flow<Resource<Tv>> {
        return object : NetworkBoundResource<Tv, TvDetailResponse?>() {
            public override fun loadFromDB(): Flow<Tv> {
                return localDataSource.getTvDetail(sid).map {
                    DataMapper.mapTveEntityToDomain(it)
                }
            }

            override suspend fun shouldFetch(data: Tv?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<TvDetailResponse?>> {
                return remoteDataSource.getTvDetail(sid)
            }

            override suspend fun saveCallResult(data: TvDetailResponse?) {
                TvEntity(
                    id = data?.id ?: 0,
                    backdropPath = data?.backdropPath,
                    originalLanguage = data?.originalLanguage,
                    posterPath = data?.posterPath,
                    voteCount = data?.voteCount,
                    voteAverage = data?.voteAverage,
                    popularity = data?.popularity,
                    overview = data?.overview,
                    firstAirDate = data?.firstAirDate,
                    originalName = data?.originalName,
                    name = data?.name
                )
            }
        }.asFlow()
    }

    override fun setFavouriteTvs(tv: Tv, state: Boolean) {
        appExecutors.diskIO()
            .execute { localDataSource.setFavouriteTv(DataMapper.mapTvDomainToEntity(tv), state) }
    }

    override fun getFavouriteTvs(): Flow<List<Tv>> {
        return localDataSource.getFavouriteTvs().map {
            DataMapper.mapTvEntitiesToDomain(it)
        }
    }

}