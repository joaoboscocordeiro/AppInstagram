package br.com.multalpha.aplicativos.v1.appinstagram.ui.register

import androidx.annotation.StringRes
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView

/**
 * Created by João Bosco on 04/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

interface RegisterEmail {

    interface Presenter: BasePresenter {
        fun create(email: String)
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun onEmailFailure(message: String)
        fun goToNameAndPasswordScreen(email: String)
    }
}