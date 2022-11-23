package com.brainstation.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.brainstation.exam.databinding.ActivityMainBinding
import com.brainstation.exam.utils.Constant.TAG
import com.brainstation.exam.utils.NetworkResult
import com.brainstation.exam.viewmodel.GitRepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val gitRepoViewModel by viewModels<GitRepoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        gitRepoViewModel.getRepoData()

        gitRepoViewModel.gitRepoResponseLiveData.observe(this){
            when(it){
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Log.d(TAG, "onCreate: ${it.message}")
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d(TAG, "onCreate: Loading-------------------")
                }
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Log.d(TAG, "onCreate: ${it.data?.items?.size}")
                }
            }
        }
    }
}