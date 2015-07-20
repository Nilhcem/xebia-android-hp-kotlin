package com.nilhcem.henripotier.core.ui.recyclerview

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class AutofitRecyclerView : RecyclerView {

    private var manager: GridLayoutManager
    private var columnWidth = -1

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.columnWidth))
            columnWidth = array.getDimensionPixelSize(0, -1)
            array.recycle()
        }

        manager = GridLayoutManager(context, 1)
        setLayoutManager(manager)
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        if (columnWidth > 0) {
            manager.setSpanCount(Math.max(1, getMeasuredWidth() / columnWidth))
        }
    }
}
