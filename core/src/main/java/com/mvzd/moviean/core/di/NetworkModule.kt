package com.mvzd.moviean.core.di

import com.mvzd.moviean.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val url = "api.themoviedb.org"

    private val certificatePinner = CertificatePinner.Builder()
        .add(url, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
        .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                "https://$url/"
            ).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        return retrofit.create(ApiService::class.java)
    }
}