package com.brainstation.exam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainstation.exam.model.GitRepoResponse
import com.brainstation.exam.repository.GitRepoRepository
import com.brainstation.exam.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepoViewModel @Inject constructor(private val gitRepoRepository: GitRepoRepository) :
    ViewModel() {

    val gitRepoResponseLiveData: LiveData<NetworkResult<GitRepoResponse>>
        get() = gitRepoRepository.gitRepoResponse

    fun getRepoData(searchQuery: String, sort: String, order: String, per_page: String) {
        viewModelScope.launch {
            gitRepoRepository.getRepoData(searchQuery, sort, order, per_page)
        }
    }

}