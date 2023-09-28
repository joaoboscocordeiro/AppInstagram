package br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data

/**
 * Created by João Bosco on 01/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface LoginCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}