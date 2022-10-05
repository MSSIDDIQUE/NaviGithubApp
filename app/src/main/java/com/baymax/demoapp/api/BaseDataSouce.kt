package com.baymax.demoapp.api

import retrofit2.Response
import com.baymax.demoapp.data.Result

abstract class BaseDataSouce {
    suspend fun <T> getResult(call:suspend()->Response<T>) : Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return Result.Failure()
        } catch (e: Exception) {
            return Result.Failure()
        }
    }
}