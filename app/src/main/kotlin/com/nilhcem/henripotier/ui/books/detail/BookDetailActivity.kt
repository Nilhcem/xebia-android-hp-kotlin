package com.nilhcem.henripotier.ui.books.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.model.Book
import kotlinx.android.synthetic.book_detail.bookTitle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.text

public class BookDetailActivity : AppCompatActivity(), AnkoLogger {

    public companion object {
        public val extraBook: String = "book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.book_detail)

        val book = getIntent().getExtras()!!.getSerializable(extraBook) as Book
        bookTitle.text = book.title
    }
}
