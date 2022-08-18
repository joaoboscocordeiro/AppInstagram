package br.com.multalpha.aplicativos.v1.appinstagram.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import br.com.multalpha.aplicativos.v1.appinstagram.databinding.DialogCustomBinding

/**
 * Created by João Bosco on 20/10/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding
    private lateinit var txtButtons: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun addButton(vararg texts: Int, listener: View.OnClickListener) {
        txtButtons = Array(texts.size) {
            TextView(context)
        }

        texts.forEachIndexed { index, txtId ->
            txtButtons[index].id = txtId
            txtButtons[index].setText(txtId)
            txtButtons[index].setOnClickListener {
                listener.onClick(it)
                dismiss()
            }
        }
    }

    override fun show() {
        super.show()

        for (textView in txtButtons) {
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30, 40, 30,40)

            binding.dialogContainer.addView(textView, layoutParams)
        }
    }
}