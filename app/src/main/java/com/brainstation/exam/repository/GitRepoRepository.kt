package com.brainstation.exam.repository

import android.util.Log
import com.brainstation.exam.api.GitRepoApi
import com.brainstation.exam.model.GitRepoResponse
import com.brainstation.exam.utils.Constant.TAG
import javax.inject.Inject

class GitRepoRepository @Inject constructor(private val gitRepoApi: GitRepoApi) {

    suspend fun getRepoData(gitRepoResponse: GitRepoResponse){
        val response = gitRepoApi.getGitRepo("android")
        Log.d(TAG, "getRepoData: ${response.body().toString()}")
    }
}