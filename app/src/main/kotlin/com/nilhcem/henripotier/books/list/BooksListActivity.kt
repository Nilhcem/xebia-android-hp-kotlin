package com.nilhcem.henripotier.books.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.nilhcem.henripotier.R
import kotlinx.android.synthetic.books_list.booksList

public class BooksListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.books_list)
        booksList.setLayoutManager(LinearLayoutManager(this))
        booksList.setAdapter(BooksListAdapter(Array(5, { i -> "Book #${i + 1}" }).toList()))
    }
}
