package com.nilhcem.henripotier.ui.books.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.nilhcem.henripotier.core.extensions.replaceAll
import com.nilhcem.henripotier.model.Book
import org.jetbrains.anko.text
import java.util.ArrayList

public class BooksListAdapter() : RecyclerView.Adapter<BooksListAdapter.ViewHolder>() {

    public var items: ArrayList<Book> = ArrayList()
        set(value) {
            $items.replaceAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.getContext()))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items.get(position).toString()
    }

    override fun getItemCount(): Int = items.size()

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
