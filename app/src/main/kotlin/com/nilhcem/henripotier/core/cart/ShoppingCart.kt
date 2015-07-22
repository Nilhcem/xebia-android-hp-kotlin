package com.nilhcem.henripotier.core.cart

import android.content.Context
import android.content.SharedPreferences

class ShoppingCart(val context: Context) {

    companion object {
        val prefsName = "shopping_cart"
    }

    val sharedPrefs: SharedPreferences

    init {
        sharedPrefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    fun getAllIbnsInCart() = sharedPrefs.getAll().filter { it.getValue() == true }.map { it.getKey() }

    fun getNbItemsInCart() = getAllIbnsInCart().count()

    fun isInCart(isbn: String) = sharedPrefs.contains(isbn)

    fun addToCart(isbn: String) = sharedPrefs.edit().putBoolean(isbn, true).commit()

    fun removeFromCart(isbn: String) = sharedPrefs.edit().remove(isbn).commit()

    fun toggleFromCart(isbn: String) {
        if (isInCart(isbn)) {
            removeFromCart(isbn)
        } else {
            addToCart(isbn)
        }
    }
}
