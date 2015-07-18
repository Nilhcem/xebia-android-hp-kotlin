package com.nilhcem.henripotier.core.extensions

import java.util.ArrayList

fun <T>ArrayList<T>.replaceAll(data: List<T>) {
    clear()
    addAll(data)
}
