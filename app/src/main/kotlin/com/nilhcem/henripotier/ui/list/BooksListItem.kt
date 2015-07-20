package com.nilhcem.henripotier.ui.list

import android.content.Context
import android.support.v7.widget.CardView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nilhcem.henripotier.R
import com.nilhcem.henripotier.core.extensions.format
import com.nilhcem.henripotier.model.Book
import org.jetbrains.anko.find
import org.jetbrains.anko.text
import org.jetbrains.anko.visibility

class BooksListItem(context: Context) : FrameLayout(context) {

    val card: CardView
    private val title: TextView
    private val cover: ImageView
    private val loading: ProgressBar

    init {
        LayoutInflater.from(context).inflate(R.layout.books_list_item, this, true)
        card = find<CardView>(R.id.book_card)
        title = find<TextView>(R.id.book_title)
        cover = find<ImageView>(R.id.book_cover)
        loading = find<ProgressBar>(R.id.book_loading)
    }

    fun bindData(book: Book) {
        cover.setImageResource(0)
        cover.visibility = View.VISIBLE
        loading.visibility = View.VISIBLE

        title.text = Html.fromHtml("${book.title}<br><b>${book.price.format(2)}€</b>")
        Glide.with(getContext()).load(book.cover).crossFade()
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean = hideLoading()
                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean = hideLoading()
                })
                .into(cover)
    }

    fun hideLoading(): Boolean {
        loading.visibility = View.GONE
        return false
    }
}
