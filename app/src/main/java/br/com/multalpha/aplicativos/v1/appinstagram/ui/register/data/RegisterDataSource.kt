package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data

import android.net.Uri

/**
 * Created by João Bosco on 01/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface RegisterDataSource {
    fun create(email: String, callback: RegisterCallback)
    fun create(email: String, name: String, password: String, callback: RegisterCallback)
    fun updateUser(photoUri: Uri, callback: RegisterCallback)
}