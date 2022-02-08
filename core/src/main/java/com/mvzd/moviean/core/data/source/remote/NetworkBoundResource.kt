package com.mvzd.moviean.core.data.source.remote

import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.loading(data = null))
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.loading(data = null))
            when(val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.error(data = null, msg = apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract suspend fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}