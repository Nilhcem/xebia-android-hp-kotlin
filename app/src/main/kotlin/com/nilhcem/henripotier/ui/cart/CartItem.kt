package com.nilhcem.henripotier.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.extensions.toBold

import kotlinx.android.synthetic.main.cart_item.view.*

class CartItem(context: Context) : LinearLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.cart_item, this, true)
    }

    fun bindData(data: CartItemData, titleInBold: Boolean) {
        with(data) {
            if (titleInBold)
                titleView.text = title.toBold() else title
            priceView.text = price
        }
    }
}
