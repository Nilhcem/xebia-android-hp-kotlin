package com.nilhcem.henripotier

import android.app.Application
import com.nilhcem.henripotier.core.cart.ShoppingCart
import kotlin.properties.Delegates

class HPApp : Application() {

    companion object {
        var cart: ShoppingCart by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        cart = ShoppingCart(this)
    }
}
