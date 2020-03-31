package com.ternovyi.redditapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ternovyi.redditapp.databinding.ItemTopEntriesBinding
import com.ternovyi.redditapp.model.container.RedditChildren

class TopEntriesPagingAdapter :
    PagedListAdapter<RedditChildren, TopEntriesAdapter.TopEntriesViewHolder>(ENTRIES_COMPARATOR) {

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
        item?.let {
            holder.binding.run {
                titleString = it.data.title
                avatarUrl = it.data.thumbnail
                authorString = it.data.author
                subredditString = it.data.subReddit
                datePosted = it.data.created
                ratingString = it.data.score.toString()
                numberOfCommentsString = it.data.numComments.toString()
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