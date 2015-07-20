package com.nilhcem.henripotier.ui.cart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.nilhcem.henripotier.R
import kotlinx.android.synthetic.cart.collapsing_toolbar
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class CartActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.cart)
        setSupportActionBar(find<Toolbar>(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_toolbar.setTitle(getString(R.string.cart_title))
    }
}
