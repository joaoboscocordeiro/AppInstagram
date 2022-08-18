package br.com.multalpha.aplicativos.v1.appinstagram.ui.add

import android.net.Uri
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView

/**
 * Created by João Bosco on 31/01/2022.
 * e-mail - Support: ti.junior@gmail.com
 */

interface Add {

    interface Presenter : BasePresenter {
        fun createPost(uri: Uri, caption: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestSuccess()
        fun displayRequestFailure(message: String)
    }

}