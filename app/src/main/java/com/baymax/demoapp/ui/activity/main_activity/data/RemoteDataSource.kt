package com.baymax.demoapp.ui.activity.main_activity.data

import com.baymax.demoapp.api.ApiService
import com.baymax.demoapp.api.BaseDataSouce

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