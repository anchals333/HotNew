package com.anchal.hotnew.utils


import android.content.Context

import android.net.ConnectivityManager

import android.os.Bundle

import android.view.View

import android.view.inputmethod.InputMethodManager

import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Fade
import com.anchal.hotnew.R


class Utility private constructor(private val context: Context?) {

    companion object Singleton {
        fun getInstance(context: Context?): Utility {
            return Utility(context)
        }
    }

    fun hideKeyBoard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isNetworkConnected(): Boolean {

        val connectivityManager =
            context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return if (activeNetworkInfo != null && activeNetworkInfo.isConnected)
            true
        else {
            showToast(context.getString(R.string.internet_error))
            false
        }
    }

    fun showToast(text: String?) {
        if (text != null)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun loadFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        manager: FragmentManager,
        args: Bundle? = null,
        transitionName: String? = null,
        view: View? = null
    ) {
        val fragmentTransaction = manager.beginTransaction()
        fragment.enterTransition = Fade()
        fragment.exitTransition = Fade()
        fragmentTransaction.replace(R.id.container, fragment)
        if (addToBackStack)
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)

        if (!transitionName.isNullOrEmpty() && view != null) {
            fragmentTransaction.addSharedElement(view, transitionName)
        }

        if (args != null)
            fragment.arguments = args

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }

}
