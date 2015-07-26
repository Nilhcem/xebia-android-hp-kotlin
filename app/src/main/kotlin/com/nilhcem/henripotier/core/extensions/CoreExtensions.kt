package com.nilhcem.henripotier.core.extensions

import android.text.Html
import android.view.View

fun Float.format(digits: Int) = java.lang.String.format("%.${digits}f", this)
fun Float.euroPrice() = "${format(2)} €"
fun String.toBold() = Html.fromHtml("<b>${this}</b>")
fun View.isVisible() = getVisibility() == View.VISIBLE
