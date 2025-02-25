package com.test.shared.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomMarginDecoration(
    private val firstTopPadding: Int,
    private val lastBottomPadding: Int,
    private val horizontalPadding: Int,
    private val topPadding: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        outRect.left = horizontalPadding
        outRect.right = horizontalPadding
        when (position) {
            0 -> outRect.top = firstTopPadding
            itemCount - 1 -> outRect.bottom = lastBottomPadding
            else -> outRect.top = topPadding
        }
    }
}