package com.zup.authenticator.core.extension

import android.text.Editable
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hidden() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun EditText?.actionDone(func: (Editable?) -> Unit) {
    this?.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            func(this.editableText)
            true
        } else false
    }
}
