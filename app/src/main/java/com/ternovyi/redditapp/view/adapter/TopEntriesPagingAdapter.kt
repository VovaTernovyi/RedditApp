package com.ternovyi.redditapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ternovyi.redditapp.databinding.ItemTopEntriesBinding
import com.ternovyi.redditapp.model.container.RedditChildren

class TopEntriesPagingAdapter :
    PagedListAdapter<RedditChildren, TopEntriesAdapter.TopEntriesViewHolder>(ENTRIES_COMPARATOR) {

    var onTopEntriesClickListener: ((url: String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopEntriesAdapter.TopEntriesViewHolder = TopEntriesAdapter.TopEntriesViewHolder(
        ItemTopEntriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TopEntriesAdapter.TopEntriesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { post ->
            holder.binding.run {
                titleString = post.data.title
                avatarUrl = post.data.thumbnail
                authorString = post.data.author
                subredditString = post.data.subReddit
                datePosted = post.data.created
                ratingString = post.data.score.toString()
                numberOfCommentsString = post.data.numComments.toString()
                root.setOnClickListener {
                    onTopEntriesClickListener?.invoke(post.data.url)
                }
            }
        }
    }

    companion object {
        private val ENTRIES_COMPARATOR = object : DiffUtil.ItemCallback<RedditChildren>() {
            override fun areItemsTheSame(
                oldItem: RedditChildren,
                newItem: RedditChildren
            ): Boolean =
                oldItem.data == newItem.data

            override fun areContentsTheSame(
                oldItem: RedditChildren,
                newItem: RedditChildren
            ): Boolean =
                newItem == oldItem
        }
    }
}