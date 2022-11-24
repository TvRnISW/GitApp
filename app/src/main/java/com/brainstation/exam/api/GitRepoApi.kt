package com.brainstation.exam.api

import com.brainstation.exam.model.GitRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoApi {
    @GET("/search/issues?")
    suspend fun getGitRepo(@Query("q") searchQuery: String, @Query("sort") sort: String, @Query("order") order: String, @Query("per_page") per_page: String): Response<GitRepoResponse>
}