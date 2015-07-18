package com.nilhcem.henripotier.ui.books.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nilhcem.henripotier.core.extensions.replaceAll
import com.nilhcem.henripotier.core.ui.ViewHolder
import com.nilhcem.henripotier.model.Book
import java.util.ArrayList

public class BooksListAdapter() : RecyclerView.Adapter<ViewHolder>() {

    public var items: ArrayList<Book> = ArrayList()
        set(value) {
            $items.replaceAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(BooksListItem(parent.getContext()))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            (holder.itemView as BooksListItem).bindData(items.get(position))

    override fun getItemCount(): Int = items.size()
}
