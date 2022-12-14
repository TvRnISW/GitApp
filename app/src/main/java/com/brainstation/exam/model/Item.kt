package com.brainstation.exam.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblrepo")
data class Item(
    val active_lock_reason: String,
    val assignee: String,
    val assignees: List<Any>,
    val author_association: String,
    val body: String,
    val closed_at: String,
    val comments: Int,
    val comments_url: String,
    val created_at: String,
    val events_url: String,
    val html_url: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val labels: List<Label>,
    val labels_url: String,
    val locked: Boolean,
    val milestone: Milestone,
    val node_id: String,
    val number: Int,
    val performed_via_github_app: Any,
    val reactions: Reactions,
    val repository_url: String,
    val score: Int,
    val state: String,
    val state_reason: String,
    val timeline_url: String,
    val title: String,
    val updated_at: String,
    val url: String,
    val user: User
)