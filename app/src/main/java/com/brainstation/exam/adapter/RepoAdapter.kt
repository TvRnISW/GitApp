package com.brainstation.exam.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brainstation.exam.databinding.RepoItemBinding
import com.brainstation.exam.model.Item
import java.text.SimpleDateFormat

class RepoAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Item, RepoAdapter.RepoViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class RepoViewHolder(private val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item?) {
            binding.txtItemTitle.text = item?.title
            binding.txtCreatedDate.text = formattedDate(item?.created_at)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formattedDate(createdAt: String?): CharSequence? {

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return createdAt?.let { it -> parser.parse(it)?.let { formatter.format(it) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val meme = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(meme)
        }
        holder.bind(meme)
    }


    class OnClickListener(val clickListener: (meme: Item) -> Unit) {
        fun onClick(meme: Item) = clickListener(meme)
    }
}