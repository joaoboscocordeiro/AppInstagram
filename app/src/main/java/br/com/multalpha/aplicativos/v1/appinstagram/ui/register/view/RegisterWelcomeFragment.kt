package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.databinding.FragmentRegisterWelcomeBinding

/**
 * Created by João Bosco on 20/10/2021.
 */
class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {

    private var binding: FragmentRegisterWelcomeBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterWelcomeBinding.bind(view)

        val name = arguments?.getString(KEY_NAME) ?: throw IllegalArgumentException("Name not found")

        binding?.let {
            with(it) {
                registerTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener {
                    fragmentAttachListener?.goToPhotoScreen()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_NAME = "key_name"
    }
}