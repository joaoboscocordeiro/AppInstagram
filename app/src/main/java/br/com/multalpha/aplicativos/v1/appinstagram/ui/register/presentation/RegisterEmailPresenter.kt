package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.presentation

import android.util.Patterns
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.RegisterEmail
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.RegisterCallback
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.RegisterRepository

/**
 * Created by João Bosco on 28/10/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterRepository
) : RegisterEmail.Presenter {

    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }
        if (isEmailValid) {
            view?.showProgress(true)

            repository.create(email, object : RegisterCallback {
                override fun onSuccess() {
                    view?.goToNameAndPasswordScreen(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }
    }

    override fun onDestroy() {
        view = null
    }
}