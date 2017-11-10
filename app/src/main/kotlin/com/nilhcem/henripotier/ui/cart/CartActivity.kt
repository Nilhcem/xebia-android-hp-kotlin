package com.nilhcem.henripotier.ui.cart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.nilhcem.henripotier.HPApp
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.cart.BestOfferCalculator
import com.nilhcem.henripotier.core.extensions.euroPrice
import com.nilhcem.henripotier.core.extensions.noNetworkSnackBar
import com.nilhcem.henripotier.model.Book
import com.nilhcem.henripotier.model.CommercialOffer
import com.nilhcem.henripotier.network.RestApi
import com.nilhcem.henripotier.ui.list.BooksListActivity
import kotlinx.android.synthetic.main.cart.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async
import retrofit.RetrofitError
import java.util.ArrayList
import java.util.Locale

class CartActivity : AppCompatActivity(), AnkoLogger {

    companion object {
        val extraBooks = "books"
        private val stateOffers = "offers"
    }

    private val cart = HPApp.cart
    private val adapter = CartAdapter(cart)

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        if (cart.getNbItemsInCart() == 0) {
            finish();
        }

        setContentView(R.layout.cart)
        setSupportActionBar(find<Toolbar>(R.id.toolbar));
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        collapsing_toolbar.setTitle(getString(R.string.cart_title))

        fab.setOnClickListener {
            alert(R.string.cart_thanks_content, R.string.cart_thanks_title) {
                positiveButton(R.string.cart_thanks_close) {
                    cart.clear()
                    startActivity(intentFor<BooksListActivity>().clearTop())
                }
            }.show()
        }

        cartList.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        cartList.setAdapter(adapter)
        getCommercialOffers(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super<AppCompatActivity>.onSaveInstanceState(outState)
        outState.putSerializable(stateOffers, adapter.items)
    }

    private fun getCommercialOffers(savedInstanceState: Bundle?) {
        info { "Get commercial offers" }

        async {
            val entries = if (savedInstanceState == null) {
                try {
                    createDataEntries(RestApi.bookStoreApi.getCommercialOffers(getCartIsbns()).offers)
                } catch (e: RetrofitError) {
                    ArrayList<CartItemData>()
                }
            } else {
                savedInstanceState.getSerializable(stateOffers) as ArrayList<CartItemData>
            }

            if (entries.isEmpty()) {
                noNetworkSnackBar(parentLayout) { getCommercialOffers(null) }
            } else {
                uiThread {
                    adapter.items = entries
                }
            }
        }
    }

    private fun getCartIsbns() = cart.getAllIsbnsInCart().joinToString(separator = ",")

    private fun createDataEntries(offers: List<CommercialOffer>): ArrayList<CartItemData> {
        val entries = ArrayList<CartItemData>()

        val isbnsInCart = cart.getAllIsbnsInCart()
        val booksInCart = (getIntent().getSerializableExtra(extraBooks) as ArrayList<Book>).filter { isbnsInCart.contains(it.isbn) }

        entries.addAll(booksInCart.map { CartItemData(it.title, it.price.euroPrice()) })

        val totalAmount: Float = booksInCart.map { it.price }.sum()
        val bestOffer = BestOfferCalculator.getBestOffer(totalAmount, offers)

        if (bestOffer == null) {
            entries.add(CartItemData(getString(R.string.cart_total), totalAmount.euroPrice()))
        } else {
            val discount = BestOfferCalculator.computeDiscount(totalAmount, bestOffer)
            entries.add(CartItemData(getString(R.string.cart_subtotal), totalAmount.euroPrice()))
            entries.add(CartItemData(getString(R.string.cart_discount, bestOffer.type.name.toLowerCase(Locale.US)), discount.euroPrice()))
            entries.add(CartItemData(getString(R.string.cart_total), (totalAmount - discount).euroPrice()))
        }
        return entries
    }
}
