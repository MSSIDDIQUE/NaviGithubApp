package com.baymax.navigithubapp.ui.activity.main_activity.data

import com.baymax.navigithubapp.api.ApiService
import com.baymax.navigithubapp.api.BaseDataSouce

class RemoteDataSource(
    private val apiService: ApiService
) : BaseDataSouce() {
    suspend fun getPrs(
        owner: String,
        repo: String
    ) = getResult {
        apiService.getPrs(owner, repo)
    }
}