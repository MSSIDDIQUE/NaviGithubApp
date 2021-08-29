package com.baymax.navigithubapp.api

import com.baymax.navigithubapp.api.response.PullRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun getPrs(
        @Path("owner")
        repoOwner: String,
        @Path("repo")
        repoName: String,
        @Query("state")
        state: String = "closed"
    ): Response<ArrayList<PullRequest>>
}