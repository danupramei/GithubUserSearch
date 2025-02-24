package com.test.shared.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomMarginDecoration(
    private val topPadding: Int,
    private val bottomPadding: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        when (position) {
            0 -> outRect.top = topPadding
            itemCount - 1 -> outRect.bottom = bottomPadding
        }
    }
}