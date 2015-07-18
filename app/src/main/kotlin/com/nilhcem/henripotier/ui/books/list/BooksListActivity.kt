package com.nilhcem.henripotier.ui.books.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.network.RestApi
import kotlinx.android.synthetic.books_list.booksList
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.ArrayList

public class BooksListActivity : AppCompatActivity(), AnkoLogger {

    private val adapter = BooksListAdapter { book ->
        Toast.makeText(this, "Selected: ${book.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.books_list)
        booksList.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        booksList.setAdapter(adapter)
    }

    override fun onResume() {
        super<AppCompatActivity>.onResume()
        getBooksList()
    }

    private fun getBooksList() {
        info { "Get books list" }

        async {
            val books = ArrayList(RestApi.bookStoreApi.getBooks())
            uiThread {
                adapter.items = books
            }
        }
    }
}
