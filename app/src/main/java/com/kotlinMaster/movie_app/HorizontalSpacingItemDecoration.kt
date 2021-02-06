package com.kotlinMaster.movie_app

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpacingItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.layoutManager !is LinearLayoutManager) return

        if ((parent.layoutManager as LinearLayoutManager).orientation == RecyclerView.HORIZONTAL) {
            view.layoutParams.width = parent.measuredWidth / 4
        }
    }
}