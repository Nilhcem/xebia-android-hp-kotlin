package com.nilhcem.henripotier.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.extensions.toBold
import kotlinx.android.synthetic.cart_item.view.priceView
import kotlinx.android.synthetic.cart_item.view.titleView
import org.jetbrains.anko.text

class CartItem(context: Context) : LinearLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.cart_item, this, true)
    }

    fun bindData(data: CartItemData, titleInBold: Boolean) {
        with (data) {
            titleView.text = if (titleInBold) title.toBold() else title
            priceView.text = price
        }
    }
}
