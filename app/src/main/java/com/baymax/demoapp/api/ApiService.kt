package com.baymax.demoapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    suspend fun getHoldings(
        @Path("owner")
        repoOwner: String,
        @Path("repo")
        repoName: String,
        @Query("state")
        state: String = "closed"
    ): Response<ArrayList<PullRequest>>
}