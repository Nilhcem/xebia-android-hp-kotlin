package com.nilhcem.henripotier.ui.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nilhcem.henripotier.HPApp
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.extensions.noNetworkSnackBar
import com.nilhcem.henripotier.model.Book
import com.nilhcem.henripotier.network.RestApi
import com.nilhcem.henripotier.ui.cart.CartActivity
import kotlinx.android.synthetic.books_list.booksList
import kotlinx.android.synthetic.books_list.cartActionButton
import kotlinx.android.synthetic.books_list.parentLayout
import kotlinx.android.synthetic.books_list.toolbar
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import retrofit.RetrofitError
import java.util.ArrayList

class BooksListActivity : AppCompatActivity(), AnkoLogger {

    private companion object {
        private val stateBooks = "books"
    }

    private val cart = HPApp.cart!!
    private val adapter = BooksListAdapter(cart) {
        updateTitle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.books_list)
        setSupportActionBar(toolbar)
        updateTitle()

        cartActionButton.setOnClickListener { startActivity<CartActivity>(CartActivity.extraBooks to adapter.items) }

        booksList.setAdapter(adapter)
        booksList.setItemAnimator(null)
        getBooksList(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super<AppCompatActivity>.onSaveInstanceState(outState)
        outState.putSerializable(stateBooks, adapter.items)
    }

    private fun updateTitle() {
        val actionBar = getSupportActionBar()
        val nbSelectedItems = cart.getNbItemsInCart()

        actionBar.setTitle(if (nbSelectedItems == 0) {
            getString(R.string.listing_title)
        } else {
            getString(R.string.listing_title_added, nbSelectedItems)
        })
    }

    private fun getBooksList(savedInstanceState: Bundle?) {
        info { "Get books list" }

        async {
            val books = if (savedInstanceState == null) {
                try {
                    ArrayList(RestApi.bookStoreApi.getBooks())
                } catch (e: RetrofitError) {
                    ArrayList<Book>()
                }
            } else {
                savedInstanceState.getSerializable(stateBooks) as ArrayList<Book>
            }

            if (books.isEmpty()) {
                noNetworkSnackBar(parentLayout) { getBooksList(null) }
            } else {
                uiThread {
                    adapter.items = books
                }
            }
        }
    }
}
