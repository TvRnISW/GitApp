package com.brainstation.exam.model

data class GitRepoResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)