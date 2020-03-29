package com.ternovyi.redditapp.view.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ternovyi.redditapp.R
import com.ternovyi.redditapp.view.MainActivity

abstract class BaseFragment : Fragment() {

    private lateinit var rootView: View
    @StringRes
    private var toolbarTitle: Int = 0

    private val mainActivity by lazy {
        activity as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle = savedInstanceState?.getInt(FRAGMENT_TOOLBAR_TITLE) ?: 0
        rootView = view
    }

    override fun onResume() {
        super.onResume()
        if (toolbarTitle != 0) {
            setToolbarTitle(toolbarTitle)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(FRAGMENT_TOOLBAR_TITLE, toolbarTitle)
        super.onSaveInstanceState(outState)
    }

    open fun showSnackbar(msg: String, length: Int) =
        mainActivity.showSnackbar(rootView, msg, length)

    open fun showSnackbar(@StringRes strRes: Int, length: Int) =
        mainActivity.showSnackbar(rootView, strRes, length)

    open fun showDefaultErrorSnackbar() = mainActivity.showDefaultErrorSnackbar(rootView)

    protected fun checkPermission(vararg permissions: String): Boolean {
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(
                    mainActivity,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }

        return true
    }

    protected fun checkAndRequestPermission(permissions: Array<String>, requestCode: Int) =
        if (checkPermission(*permissions)) {
            true
        } else {
            requestPermissions(permissions, requestCode)
            false
        }

    protected open fun clearBackStack(tag: String? = null) {
        mainActivity.supportFragmentManager.popBackStack(
            tag,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    protected fun setToolbarTitle(@StringRes title: Int) {
        toolbarTitle = title
        getToolbar()?.setTitle(title)
    }

    private fun saveToolbarTitle(value: Int, savedInstanceState: Bundle) {
        savedInstanceState.putInt(FRAGMENT_TOOLBAR_TITLE, value)
    }

    protected fun getToolbar() = mainActivity.supportActionBar

    protected open fun switchFragments(
        fragment: Fragment,
        @AnimRes enter: Int = 0,
        @AnimRes exit: Int = 0,
        @AnimRes backEnter: Int = 0,
        @AnimRes backExit: Int = 0
    ) = replace(fragment, enter, exit, backEnter, backExit).commit()

    protected open fun replaceFragmentPlusBackStack(
        fragment: Fragment,
        tag: String? = null,
        @AnimRes enter: Int = 0,
        @AnimRes exit: Int = 0,
        @AnimRes backEnter: Int = 0,
        @AnimRes backExit: Int = 0
    ) = replace(fragment, enter, exit, backEnter, backExit).addToBackStack(tag).commit()

    protected open fun getManager() = parentFragmentManager

    private fun replace(
        fragment: Fragment,
        @AnimRes enter: Int,
        @AnimRes exit: Int,
        @AnimRes backEnter: Int,
        @AnimRes backExit: Int
    ) =
        getManager()
            .beginTransaction()
            .setCustomAnimations(enter, exit, backEnter, backExit)
            .replace(R.id.main_container, fragment)

    companion object {
        private const val FRAGMENT_TOOLBAR_TITLE = "FRAGMENT_TOOLBAR_TITLE"
    }
}