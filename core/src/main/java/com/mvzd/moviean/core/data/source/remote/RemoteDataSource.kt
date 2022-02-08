package com.mvzd.moviean.core.data.source.remote

import com.mvzd.moviean.core.data.source.remote.network.ApiService
import com.mvzd.moviean.core.data.source.remote.response.movie.MovieDetailResponse
import com.mvzd.moviean.core.data.source.remote.response.movie.MovieResponse
import com.mvzd.moviean.core.data.source.remote.response.tv.TvDetailResponse
import com.mvzd.moviean.core.data.source.remote.response.tv.TvResponse
import com.mvzd.moviean.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = apiService.getTopMovies()
                val dataArray = response.results
                if (dataArray != null && dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getMovieDetail(id: Int): Flow<ApiResponse<MovieDetailResponse?>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                EspressoIdlingResource.increment()
                val response = apiService.getMovieDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getAllTvs(): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = apiService.getTopTvs()
                val dataArray = response.results
                if (dataArray != null && dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getTvDetail(id: Int): Flow<ApiResponse<TvDetailResponse?>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = apiService.getTvDetail(id)
                emit(ApiResponse.Success(response))
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }
}