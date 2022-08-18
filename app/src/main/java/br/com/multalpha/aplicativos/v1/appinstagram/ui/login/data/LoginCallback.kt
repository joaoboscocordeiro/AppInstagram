package br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 01/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface LoginCallback {
    fun onSuccess(userAuth: UserAuth)
    fun onFailure(message: String)
    fun onComplete()
}