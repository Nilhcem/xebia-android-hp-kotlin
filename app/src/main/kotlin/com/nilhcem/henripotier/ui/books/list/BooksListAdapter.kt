package com.nilhcem.henripotier.ui.books.list

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import com.nilhcem.henripotier.core.extensions.createHolder
import com.nilhcem.henripotier.core.extensions.getView
import com.nilhcem.henripotier.core.extensions.replaceAll
import com.nilhcem.henripotier.model.Book
import java.util.ArrayList

public class BooksListAdapter() : RecyclerView.Adapter<ViewHolder>() {

    public var items: ArrayList<Book> = ArrayList()
        set(value) {
            $items.replaceAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            createHolder(BooksListItem(parent.getContext()))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            getView<BooksListItem>(holder).bindData(items.get(position))

    override fun getItemCount(): Int = items.size()
}
