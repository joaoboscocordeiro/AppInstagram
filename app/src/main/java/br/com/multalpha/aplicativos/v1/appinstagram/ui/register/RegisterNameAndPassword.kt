package br.com.multalpha.aplicativos.v1.appinstagram.ui.register

import androidx.annotation.StringRes
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView

/**
 * Created by João Bosco on 06/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface RegisterNameAndPassword {

    interface Presenter : BasePresenter {
        fun create(email: String, name: String, password: String, confirm: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displayPasswordFailure(@StringRes passError: Int?)
        fun onCreateFailure(message: String)
        fun onCreateSuccess(name: String)
    }
}