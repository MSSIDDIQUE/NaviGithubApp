package com.baymax.navigithubapp.api

import retrofit2.Response
import com.baymax.navigithubapp.data.Result

abstract class BaseDataSouce {
    suspend fun <T> getResult(call:suspend()->Response<T>) : Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return Result.Failure(msg = response.message().toString())
        } catch (e: Exception) {
            return Result.Failure(msg = e.message.toString())
        }
    }
}