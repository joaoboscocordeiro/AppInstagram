package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.presentation

import android.net.Uri
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.RegisterPhoto
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.RegisterCallback
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.RegisterRepository

/**
 * Created by João Bosco on 28/10/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class RegisterPhotoPresenter(
    private var view: RegisterPhoto.View?,
    private val repository: RegisterRepository
) : RegisterPhoto.Presenter {
    override fun updateUser(photoUri: Uri) {
        view?.showProgress(true)

        repository.updateUser(photoUri, object : RegisterCallback {
            override fun onSuccess() {
                view?.onUpdateSuccess()
            }

            override fun onFailure(message: String) {
                view?.onUpdateFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}