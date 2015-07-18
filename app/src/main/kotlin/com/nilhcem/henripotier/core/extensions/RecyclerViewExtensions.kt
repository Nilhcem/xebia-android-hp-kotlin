package com.nilhcem.henripotier.core.extensions

import android.support.v7.widget.RecyclerView
import android.view.View
import com.nilhcem.henripotier.core.ui.ViewHolder

fun <T : View> RecyclerView.Adapter<ViewHolder>.getView(holder: ViewHolder): T = (holder.itemView as T)
