package com.brainstation.exam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainstation.exam.repository.GitRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepoViewModel @Inject constructor(private val gitRepoRepository: GitRepoRepository): ViewModel() {

    fun getRepoData(){
        viewModelScope.launch {
            gitRepoRepository.getRepoData()
        }
    }

}