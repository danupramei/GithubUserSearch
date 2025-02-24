package com.test.shared.extens

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.clickWithDebounce(debounceTime: Long = 500L, action: (View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun ImageView.loadNetworkImage(imageUrl: String?, defaultImageResourceId: Int? = null) {
    imageUrl?.let {
        Glide.with(this)
            .load(it)
            .error(defaultImageResourceId)
            .into(this)
    }
}