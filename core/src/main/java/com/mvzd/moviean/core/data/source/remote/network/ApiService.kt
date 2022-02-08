package com.mvzd.moviean.core.data.source.remote.network

import com.mvzd.moviean.core.data.source.remote.response.movie.ListMovieResponse
import com.mvzd.moviean.core.data.source.remote.response.movie.MovieDetailResponse
import com.mvzd.moviean.core.data.source.remote.response.tv.ListTvResponse
import com.mvzd.moviean.core.data.source.remote.response.tv.TvDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val API_KEY = "api_key=2174d146bb9c0eab47529b2e77d6b526"
    }

    @GET("3/movie/top_rated?${API_KEY}")
    suspend fun getTopMovies(): ListMovieResponse

    @GET("3/movie/{id}?${API_KEY}")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieDetailResponse

    @GET("3/tv/top_rated?${API_KEY}")
    suspend fun getTopTvs(): ListTvResponse

    @GET("3/tv/{id}?${API_KEY}")
    suspend fun getTvDetail(@Path("id") id: Int): TvDetailResponse
}