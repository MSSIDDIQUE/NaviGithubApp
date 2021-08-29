package com.baymax.navigithubapp.ui.activity.main_activity.data

class Repository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun fetchPrs(
        owner: String,
        repo: String
    ) = remoteDataSource.getPrs(owner, repo)
}