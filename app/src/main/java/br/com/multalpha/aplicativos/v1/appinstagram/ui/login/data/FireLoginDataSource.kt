package br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by João Bosco on 29/08/2022.
 */
class FireLoginDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->
                if (res.user != null) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Usuário não encontrado.")
                }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao fazer login.")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
}