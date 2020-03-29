package com.ternovyi.redditapp.view

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.ternovyi.redditapp.R
import com.ternovyi.redditapp.databinding.ActivityMainBinding
import com.ternovyi.redditapp.view.fragment.RedditTopFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var backStackChangedListener: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setupView()
    }

    override fun onDestroy() {
        unregisterCallbacks()
        super.onDestroy()
    }

    fun showSnackbar(v: View, msg: String, length: Int) = Snackbar.make(v, msg, length).show()

    fun showSnackbar(v: View, @StringRes strRes: Int, length: Int) =
        showSnackbar(v, getString(strRes), length)

    fun showDefaultErrorSnackbar(v: View) =
        showSnackbar(v, getString(R.string.error_has_occurred), Snackbar.LENGTH_LONG)

    private fun init() {
        backStackChangedListener = {
            if (supportFragmentManager.backStackEntryCount > 0) {
                showBackArrow()
            } else {
                showBackArrow(false)
            }
        }
    }

    private fun showBackArrow(show: Boolean = true) = supportActionBar?.run {
        setDisplayHomeAsUpEnabled(show)
        setDisplayShowHomeEnabled(show)
    }

    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun setupInitialFragment(isLoggedIn: Boolean) {
        val fragment = RedditTopFragment.newInstance()
        switchFragment(fragment)
    }

    private fun setupView() {
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.apply {
            setSupportActionBar(toolbar)
        }
        registerCallbacks()
    }

    private fun registerCallbacks() {
        supportFragmentManager.addOnBackStackChangedListener(backStackChangedListener)
    }

    private fun unregisterCallbacks() {
        supportFragmentManager.removeOnBackStackChangedListener(backStackChangedListener)
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.mainContainer.id, fragment)
            .commit()
    }
}
