package com.nilhcem.henripotier.core.extensions

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup

fun RecyclerView.Adapter<ViewHolder>.createHolder(view: ViewGroup) = object : RecyclerView.ViewHolder(view) {}
fun <T : View> RecyclerView.Adapter<ViewHolder>.getView(holder: ViewHolder) = holder.itemView as T
