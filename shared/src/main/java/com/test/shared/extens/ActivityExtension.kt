package com.test.shared.extens

import android.app.Activity
import android.widget.Toast

fun Activity.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}