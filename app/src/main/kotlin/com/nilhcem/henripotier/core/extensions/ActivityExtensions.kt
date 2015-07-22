package com.nilhcem.henripotier.core.extensions

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.nilhcem.henripotier.R

fun AppCompatActivity.noNetworkSnackBar(parentView: View, retryListener: () -> Unit) {
    Snackbar.make(parentView, R.string.network_issue, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.try_again) {
                retryListener.invoke()
            }.show()
}
