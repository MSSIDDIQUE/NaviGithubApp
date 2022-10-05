package com.baymax.demoapp.data

sealed class Result<T>(val data: T? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Failure<T>(data: T? = null) : Result<T>(data)
    class Loading<T> : Result<T>()
}