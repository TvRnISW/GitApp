package com.brainstation.exam.api

import com.brainstation.exam.model.GitRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitRepoApi {

    @GET("/search/issues")
    suspend fun getGitRepo(
        @Path("q") query: String,
    ):Response<GitRepoResponse>


}