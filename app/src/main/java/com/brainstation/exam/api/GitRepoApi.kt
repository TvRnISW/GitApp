package com.brainstation.exam.api

import com.brainstation.exam.model.GitRepoResponse
import retrofit2.Response
import retrofit2.http.GET

interface GitRepoApi {

    @GET("/search/issues?q=android&sort=created&order=asc&per_page=10")
    suspend fun getGitRepo(
    ):Response<GitRepoResponse>
}