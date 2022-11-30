package com.brainstation.exam.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brainstation.exam.api.GitRepoApi
import com.brainstation.exam.model.GitRepoResponse
import com.brainstation.exam.utils.Constant.TAG
import com.brainstation.exam.utils.NetworkResult
import javax.inject.Inject

class GitRepoRepository @Inject constructor(private val gitRepoApi: GitRepoApi) {

    private val _gitRepoResponseLiveData = MutableLiveData<NetworkResult<GitRepoResponse>>()
    val gitRepoResponse : LiveData<NetworkResult<GitRepoResponse>> get() = _gitRepoResponseLiveData

    suspend fun getRepoData(){
        _gitRepoResponseLiveData.postValue(NetworkResult.Loading())
        val response = gitRepoApi.getGitRepo()
        if (response.isSuccessful){
            _gitRepoResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else if (response.errorBody() != null){
            _gitRepoResponseLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }else{
            // This is big new feature
            _gitRepoResponseLiveData.postValue(NetworkResult.Error("Something went wrong!"))
            // another comment
            Log.d(TAG, "getRepoData: ")
        }
    }
}