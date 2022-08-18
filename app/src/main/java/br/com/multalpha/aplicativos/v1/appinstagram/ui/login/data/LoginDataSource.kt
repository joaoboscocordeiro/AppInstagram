package br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data

/**
 * Created by João Bosco on 01/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface LoginDataSource {
    fun login(email: String, password: String, callback: LoginCallback)
}