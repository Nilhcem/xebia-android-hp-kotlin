package com.nilhcem.henripotier.ui.list

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import com.nilhcem.henripotier.core.cart.ShoppingCart
import com.nilhcem.henripotier.core.extensions.createHolder
import com.nilhcem.henripotier.core.extensions.getView
import com.nilhcem.henripotier.core.extensions.replaceAll
import com.nilhcem.henripotier.model.Book
import java.util.ArrayList

class BooksListAdapter(val cart: ShoppingCart, val clickListener: (book: Book, position: Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    var items: ArrayList<Book> = ArrayList()
        set(value) {
            $items.replaceAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            createHolder(BooksListItem(parent.getContext()))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = items.get(position)
        val view = getView<BooksListItem>(holder)

        view.bindData(book)
        view.card.setOnClickListener() {
            if (cart.isInCart(book.isbn)) {
                cart.removeFromCart(book.isbn)
                view.setAlpha(1.0f)
            } else {
                cart.addToCart(book.isbn)
                view.setAlpha(0.5f)
            }
            clickListener.invoke(book, position)
        }
    }

    override fun getItemCount(): Int = items.size()
}
