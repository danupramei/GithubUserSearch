package com.test.shared.customview

import android.content.Context
import com.test.shared.base.AlertDialog
import com.test.shared.databinding.LayoutLoadingDialogBinding

class LoadingDialog private constructor(context: Context?) {
    private val alertDialog =
        context?.let { AlertDialog.Builder(it, LayoutLoadingDialogBinding::inflate) }

    init {
        alertDialog?.apply {
            setCanceledOnTouchOutside(false)
            setIsCancelable(false)
            build()
        }
    }

    fun show() {
        alertDialog?.show()
    }

    fun dismiss() {
        alertDialog?.dismiss()
    }

    companion object {
        fun getInstance(context: Context?): LoadingDialog = LoadingDialog(context)
    }
}