package com.ternovyi.redditapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ternovyi.redditapp.databinding.ItemTopEntriesBinding
import com.ternovyi.redditapp.model.container.RedditChildren

class TopEntriesAdapter : RecyclerView.Adapter<TopEntriesAdapter.TopEntriesViewHolder>() {

    private val topEntriesList: ArrayList<RedditChildren> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopEntriesViewHolder =
        TopEntriesViewHolder(
            ItemTopEntriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = topEntriesList.size

    override fun onBindViewHolder(holder: TopEntriesViewHolder, position: Int) {
        topEntriesList[position].run {
            holder.binding.run {
                titleString = data.title
                avatarUrl = data.thumbnail
                authorString = data.author
                subredditString = data.subReddit
                datePosted = data.created.toString()
                ratingString = data.numComments.toString()
                numberOfCommentsString = data.numComments.toString()
            }
        }
    }

    fun clearAndAddEntries(list: ArrayList<RedditChildren>) {
        if (topEntriesList == list) {
            return
        }
        topEntriesList.clear()
        topEntriesList.addAll(list)
        notifyDataSetChanged()
    }

    class TopEntriesViewHolder(
        val binding: ItemTopEntriesBinding
    ) : RecyclerView.ViewHolder(binding.root)
}