package com.nilhcem.henripotier.ui.list

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.nilhcem.henripotier.HPApp
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.extensions.isVisible
import com.nilhcem.henripotier.core.extensions.noNetworkSnackBar
import com.nilhcem.henripotier.model.Book
import com.nilhcem.henripotier.network.RestApi
import com.nilhcem.henripotier.ui.cart.CartActivity
import kotlinx.android.synthetic.main.books_list.*
import kotlinx.coroutines.experimental.async

import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async
import retrofit.RetrofitError
import java.util.ArrayList

class BooksListActivity : AppCompatActivity(), AnkoLogger {

    private companion object {
        private val stateBooks = "books"
    }

    private val cart = HPApp.cart
    private val adapter = BooksListAdapter(cart) {
        showHideCart()
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

        actionBar?.title =(if (nbSelectedItems == 0) {
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
                    cartActionButton.visibility = if (cart.getNbItemsInCart() == 0) View.INVISIBLE else View.VISIBLE
                    adapter.items = books
                }
            }
        }
    }

    private fun showHideCart() {
        val itemsInCart = cart.getNbItemsInCart()
        if (itemsInCart == 0 && cartActionButton.isVisible()) {
            val animator: ObjectAnimator = ObjectAnimator.ofFloat(cartActionButton, View.ALPHA, 1f, 0f)
            animator.setDuration(300L)
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    cartActionButton.visibility = View.INVISIBLE
                }
            })
            animator.start()
        } else if (itemsInCart != 0 && !cartActionButton.isVisible()) {
            val animator: ObjectAnimator = ObjectAnimator.ofFloat(cartActionButton, View.ALPHA, 0f, 1f)
            animator.setDuration(300L)
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    cartActionButton.visibility = View.VISIBLE
                }
            })
            animator.start()
        }
    }
}
