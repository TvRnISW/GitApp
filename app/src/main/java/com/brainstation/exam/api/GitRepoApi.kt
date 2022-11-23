package com.brainstation.exam.api

import com.brainstation.exam.model.GitRepoResponse
import com.brainstation.exam.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoApi {

    @GET("/search/issues?q=android")
    suspend fun getGitRepo(
    ):Response<GitRepoResponse>
}