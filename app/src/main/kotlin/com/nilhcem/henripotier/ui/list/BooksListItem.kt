package com.nilhcem.henripotier.ui.list

import android.content.Context
import android.support.v7.widget.CardView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.extensions.euroPrice
import com.nilhcem.henripotier.model.Book
import kotlinx.android.synthetic.main.books_list_item.view.*
import org.jetbrains.anko.find

class BooksListItem(context: Context) : FrameLayout(context) {

    val card: CardView

    init {
        LayoutInflater.from(context).inflate(R.layout.books_list_item, this, true)
        card = find(R.id.cardView)
    }

    fun bindData(book: Book) {
        with (book) {
            coverView.setImageResource(0)
            coverView.visibility = View.VISIBLE
            loadingView.visibility = View.VISIBLE

            titleView.text = Html.fromHtml("${title}<br><b>${price.euroPrice()}</b>")
            Glide.with(getContext()).load(cover).crossFade()
                    .listener(object : RequestListener<String, GlideDrawable> {
                        override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean = hideLoading()
                        override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean = hideLoading()
                    })
                    .into(coverView)
        }
    }

    fun hideLoading(): Boolean {
        loadingView.visibility = View.GONE
        return false
    }
}
