package com.nilhcem.henripotier.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.extensions.toBold
import org.jetbrains.anko.find
import org.jetbrains.anko.text

class CartItem(context: Context) : LinearLayout(context) {

    private val title: TextView
    private val price: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.cart_item, this, true)
        title = find(R.id.cart_item_title)
        price = find(R.id.cart_item_price)
    }

    fun bindData(data: CartItemData, titleInBold: Boolean) {
        title.text = if (titleInBold) data.title.toBold() else data.title
        price.text = data.price
    }
}
