package com.ternovyi.redditapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ternovyi.redditapp.R
import com.ternovyi.redditapp.databinding.FragmentRedditTopBinding

class RedditTopFragment : BaseFragment() {

    private lateinit var binding: FragmentRedditTopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRedditTopBinding.inflate(inflater, container, false).also {
        binding = it
        setupView()
    }.root

    private fun setupView() = binding.run {
        setToolbarTitle(R.string.app_name)
    }

    companion object {

        fun newInstance() = RedditTopFragment()
    }

}
