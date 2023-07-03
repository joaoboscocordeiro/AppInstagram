package br.com.multalpha.aplicativos.v1.appinstagram.ui.login

import androidx.annotation.StringRes
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView

/**
 * Created by João Bosco on 28/10/2021.
 */

interface Login {

    interface Presenter : BasePresenter{
        fun login(email: String, password: String)
    }

    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }
}