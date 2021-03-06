package com.baymax.navigithubapp.data

sealed class Result<T>(val data: T? = null, msg: String? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Failure<T>(data: T? = null, msg: String?) : Result<T>(data, msg)
    class Loading<T> : Result<T>()
}