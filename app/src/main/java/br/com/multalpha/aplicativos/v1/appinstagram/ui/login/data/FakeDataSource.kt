package br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data

import android.os.Handler
import android.os.Looper
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Database

/**
 * Created by João Bosco on 01/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            // Verificar se o e-mail digitado é igual o mesmo que esta no DB.
            val userAuth = Database.usersAuth.firstOrNull { user -> email == user.email }

            when {
                userAuth == null -> {
                    callback.onFailure("Usuário não encontrado!")
                }
                userAuth.password != password -> {
                    callback.onFailure("Senha está incorreta!")
                }
                else -> {
                    Database.sessionAuth = userAuth
                    callback.onSuccess(userAuth)
                }
            }
            callback.onComplete()
        }, 2000)
    }
}