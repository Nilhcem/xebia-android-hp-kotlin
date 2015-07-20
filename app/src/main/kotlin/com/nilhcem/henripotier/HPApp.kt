package com.nilhcem.henripotier

import android.app.Application
import com.nilhcem.henripotier.core.cart.ShoppingCart

class HPApp : Application() {

    private var cart: ShoppingCart? = null

    override fun onCreate() {
        super.onCreate()
        cart = ShoppingCart(this)
    }

    fun getCart(): ShoppingCart = cart!!
}
