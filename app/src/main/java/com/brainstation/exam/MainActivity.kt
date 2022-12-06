package com.brainstation.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brainstation.exam.adapter.RepoAdapter
import com.brainstation.exam.databinding.ActivityMainBinding
import com.brainstation.exam.utils.Constant.TAG
import com.brainstation.exam.utils.NetworkResult
import com.brainstation.exam.viewmodel.GitRepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RepoAdapter

    private val gitRepoViewModel by viewModels<GitRepoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Toast.makeText(applicationContext,"This is message",Toast.LENGTH_LONG).show()

        gitRepoViewModel.getRepoData("android","created","asc+","10")

        adapter = RepoAdapter(RepoAdapter.OnClickListener{

        })

        binding.recyclerRepoList.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerRepoList.adapter = adapter

        gitRepoViewModel.gitRepoResponseLiveData.observe(this){
            binding.progressBar.isVisible = false
            when(it){
                is NetworkResult.Error -> {
                    binding.progressBar.isVisible = false
                    Log.d(TAG, "onCreate: ${it.message}")
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                    Log.d(TAG, "onCreate: Loading-------------------")
                }
                is NetworkResult.Success -> {
                    binding.progressBar.isVisible = false
                    adapter.submitList(it.data?.items)
                    Log.d(TAG, "onCreate: ${it.data?.items?.size}")
                }
            }
        }
    }
}