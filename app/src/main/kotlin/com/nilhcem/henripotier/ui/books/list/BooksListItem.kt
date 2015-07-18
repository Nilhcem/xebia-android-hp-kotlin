package com.nilhcem.henripotier.ui.books.list

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.model.Book
import org.jetbrains.anko.find
import org.jetbrains.anko.text

public class BooksListItem(context: Context) : LinearLayout(context) {

    val bookTitle: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.books_list_item, this, true)
        bookTitle = find<TextView>(R.id.bookTitle)
    }

    public fun bindData(item: Book) {
        bookTitle.text = item.title
    }
}
