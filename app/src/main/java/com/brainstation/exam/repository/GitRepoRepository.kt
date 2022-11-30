package com.brainstation.exam.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brainstation.exam.api.GitRepoApi
import com.brainstation.exam.model.GitRepoResponse
import com.brainstation.exam.roomdb.RepoDatabase
import com.brainstation.exam.utils.NetworkResult
import com.brainstation.exam.utils.NetworkUtil
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class GitRepoRepository @Inject constructor(private val gitRepoApi: GitRepoApi, private val database: RepoDatabase) {

    private val _gitRepoResponseLiveData = MutableLiveData<NetworkResult<GitRepoResponse>>()
    val gitRepoResponse: LiveData<NetworkResult<GitRepoResponse>> get() = _gitRepoResponseLiveData



    suspend fun getRepoData(searchQuery: String, sort: String, order: String, per_page: String) {
       if (NetworkUtil.internetIsConnected()){
           _gitRepoResponseLiveData.postValue(NetworkResult.Loading())
           val response = gitRepoApi.getGitRepo(searchQuery, sort, order, per_page)
           handleResponse(response)
       }else{
           //_gitRepoResponseLiveData.postValue(NetworkResult.Success(database.repoDao().getRepoDataFromRoom()))
       }
    }



    suspend fun handleResponse(response: Response<GitRepoResponse>) {
        if (response.isSuccessful) {
            database.repoDao().insert(response.body()!!.items)
            _gitRepoResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _gitRepoResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _gitRepoResponseLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }
}