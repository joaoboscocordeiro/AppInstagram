package br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by João Bosco on 08/02/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuário não logado!")
    }
}