package com.nilhcem.henripotier

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.text

public class BooksListAdapter(val items: List<String>) : RecyclerView.Adapter<BooksListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListAdapter.ViewHolder {
        return ViewHolder(TextView(parent.getContext()))
    }

    override fun onBindViewHolder(holder: BooksListAdapter.ViewHolder, position: Int) {
        holder.textView.text = items.get(position)
    }

    override fun getItemCount(): Int = items.size()

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
