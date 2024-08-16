package com.app.cmpproject.data.remote

sealed class NetworkResult<out T>(val status: ApiStatus, val data: T?, val message: String?) {

    data class Success<out T>(val _data: T?) :
        NetworkResult<T>(status = ApiStatus.SUCCESS, data = _data, message = null)

    data class Error<out T>(val _data: T?, val exception: String) :
        NetworkResult<T>(status = ApiStatus.ERROR, data = _data, message = exception)

    data class Loading<out T>(val isLoading: Boolean) :
        NetworkResult<T>(status = ApiStatus.LOADING, data = null, message = null)

}

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING,
}