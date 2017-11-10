package com.nilhcem.henripotier.ui.cart

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nilhcem.henripotier.core.cart.ShoppingCart
import com.nilhcem.henripotier.core.extensions.createHolder
import com.nilhcem.henripotier.core.extensions.getView
import com.nilhcem.henripotier.core.extensions.replaceAll
import java.util.ArrayList

class CartAdapter(val cart: ShoppingCart) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<CartItemData> = ArrayList()
        set(value) {
            items.replaceAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            createHolder(CartItem(parent.getContext()))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            getView<CartItem>(holder).bindData(items.get(position), position >= cart.getNbItemsInCart())

    override fun getItemCount(): Int = items.size
}
