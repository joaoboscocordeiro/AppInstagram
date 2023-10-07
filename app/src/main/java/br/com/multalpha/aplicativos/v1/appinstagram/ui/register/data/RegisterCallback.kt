package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data

/**
 * Created by João Bosco on 01/11/2021.
 */
interface RegisterCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}