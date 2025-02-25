package com.test.presentation.utils

import android.view.View

inline fun String?.ifNotNullOrBlankThenElse(block: (String) -> Unit, elseBlock: () -> Unit) {
  if (!this.isNullOrBlank()) {
    block(this)
  } else {
    elseBlock()
  }
}

fun View.hideView() {
  visibility = View.GONE
}

fun View.showView() {
  visibility = View.VISIBLE
}