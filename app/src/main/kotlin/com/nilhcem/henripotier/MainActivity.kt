package com.nilhcem.henripotier

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.activity_main.booksList

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        booksList.setLayoutManager(LinearLayoutManager(this))
        booksList.setAdapter(BooksListAdapter(Array(5, { i -> "Book #${i + 1}" }).toList()))
    }
}
