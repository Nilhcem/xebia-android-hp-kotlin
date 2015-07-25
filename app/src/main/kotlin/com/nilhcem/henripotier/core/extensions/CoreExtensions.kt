package com.nilhcem.henripotier.core.extensions

fun Float.format(digits: Int) = java.lang.String.format("%.${digits}f", this)
fun Float.euroPrice() = "${format(2)} €"
