package com.ternovyi.redditapp.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import com.ternovyi.redditapp.R
import com.ternovyi.redditapp.databinding.FragmentRedditTopBinding
import com.ternovyi.redditapp.view.adapter.TopEntriesPagingAdapter
import com.ternovyi.redditapp.viewModel.TopEntriesViewModel
import kotlinx.android.synthetic.main.fragment_reddit_top.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class RedditTopFragment : BaseFragment() {

    private lateinit var binding: FragmentRedditTopBinding
    private val viewModel: TopEntriesViewModel by viewModel()

    private val topAdapter: TopEntriesPagingAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRedditTopBinding.inflate(inflater, container, false).also {
        binding = it
        setupView()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        top_recycler_view.apply {
            adapter = topAdapter
        }
        setupObservers()
    }

    private fun setupObservers() = viewModel.run {
        topEntriesPagedList.observe(viewLifecycleOwner, Observer {
            topAdapter.submitList(it)
        })
    }

    private fun setupView() = binding.apply {
        setToolbarTitle(R.string.app_name)
        topAdapter.onTopEntriesClickListener = {
            openUrl(it)
        }
    }

    private fun openUrl(url: String) {
        val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder()
            .addDefaultShareMenuItem()
            .setToolbarColor(
                this.resources.getColor(R.color.colorPrimary)
            )
            .setShowTitle(true)
            .build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    companion object {

        fun newInstance() = RedditTopFragment()
    }

}
