package com.mvzd.moviean.core.data.source.remote

data class Resource<T>(val status: StatusResponse, val data: T?, val message: String?) {
    companion object {
        fun  <T> success(data: T?): Resource<T> = Resource(StatusResponse.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(StatusResponse.ERROR, data, msg)

        fun <T> loading(data: T?): Resource<T> = Resource(StatusResponse.LOADING, data, null)
    }
}
