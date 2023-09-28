package br.com.multalpha.aplicativos.v1.appbikes.util

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by João Bosco on 15/08/2021.
 */

class TextWatcher(private val onTextChanged: (String) -> Unit) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // no used
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged(s.toString())
    }

    override fun afterTextChanged(s: Editable?) {
        // no used
    }

}