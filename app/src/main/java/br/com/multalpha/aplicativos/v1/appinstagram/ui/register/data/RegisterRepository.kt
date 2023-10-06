package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data

import android.net.Uri

/**
 * Created by João Bosco on 01/11/2021.
 */

class RegisterRepository(
    private val dataSource: RegisterDataSource
) {
    fun create(email: String,  callback: RegisterCallback) {
        dataSource.create(email, callback)
    }

    fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        dataSource.create(email, name, password, callback)
    }

    fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        dataSource.updateUser(photoUri, callback)
    }
}