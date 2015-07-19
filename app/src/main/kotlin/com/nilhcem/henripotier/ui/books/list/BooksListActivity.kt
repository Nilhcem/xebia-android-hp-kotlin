package com.nilhcem.henripotier.ui.books.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.model.Book
import com.nilhcem.henripotier.network.RestApi
import com.nilhcem.henripotier.ui.books.detail.BookDetailActivity
import kotlinx.android.synthetic.books_list.booksList
import kotlinx.android.synthetic.books_list.cartActionButton
import kotlinx.android.synthetic.books_list.toolbar
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import java.util.ArrayList

public class BooksListActivity : AppCompatActivity(), AnkoLogger {

    private companion object {
        private val stateBooks = "books"
    }

    private val adapter = BooksListAdapter { book ->
        startActivity<BookDetailActivity>(BookDetailActivity.extraBook to book)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.books_list)
        setSupportActionBar(toolbar)

        cartActionButton.setOnClickListener {
            Toast.makeText(this, "Cart!", Toast.LENGTH_SHORT).show()
        }
        booksList.setAdapter(adapter)
        getBooksList(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super<AppCompatActivity>.onSaveInstanceState(outState)
        outState.putSerializable(stateBooks, adapter.items)
    }

    private fun getBooksList(savedInstanceState: Bundle?) {
        info { "Get books list" }

        async {
            val books = if (savedInstanceState == null) {
                ArrayList(RestApi.bookStoreApi.getBooks())
            } else {
                savedInstanceState.getSerializable(stateBooks) as ArrayList<Book>
            }

            uiThread {
                adapter.items = books
            }
        }
    }
}
