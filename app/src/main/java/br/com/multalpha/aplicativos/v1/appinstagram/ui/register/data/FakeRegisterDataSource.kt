package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Database
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth
import java.util.*

/**
 * Created by João Bosco on 01/11/2021.
 */
class FakeRegisterDataSource : RegisterDataSource {

    override fun create(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { user -> email == user.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure(R.string.user_registed.toString())
            }
            callback.onComplete()
        }, 2000)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { user -> email == user.email }

            if (userAuth != null) {
                callback.onFailure("Usuário já existe!")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password, null)
//                val created = Database.usersAuth.add(newUser)
//
//                if (created) {
//                    Database.sessionAuth = newUser
//
//                    Database.followers[newUser.uuid] = hashSetOf()
//                    Database.posts[newUser.uuid] = hashSetOf()
//                    Database.feeds[newUser.uuid] = hashSetOf()
//
//                    callback.onSuccess()
//                } else {
//                    callback.onFailure("Error internal no server...")
//                }
            }
            callback.onComplete()
        }, 2000)
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.sessionAuth

//            if (userAuth == null) {
//                callback.onFailure("Usuário não encontrado!")
//            } else {
//                val index = Database.usersAuth.indexOf(Database.sessionAuth)
//                Database.usersAuth[index] = Database.sessionAuth!!.copy(photoUri = photoUri)
//                Database.sessionAuth = Database.usersAuth[index]
//
//                callback.onSuccess()
//            }
            callback.onComplete()
        }, 2000)
    }
}
