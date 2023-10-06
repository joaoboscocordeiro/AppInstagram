package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data

import android.net.Uri
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

/**
 * Created by João Bosco on 25/08/2022.
 */
class FireRegisterDataSource : RegisterDataSource {

    override fun create(email: String, callback: RegisterCallback) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Usuário já cadastrado!")
                }
            }
            .addOnFailureListener { excepcion ->
                callback.onFailure(excepcion.message ?: "Erro interno no servidor")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid
                if (uid == null) {
                    callback.onFailure("Error no server")
                } else {
                    FirebaseFirestore.getInstance()
                        .collection("/users")
                        .document(uid)
                        .set(
                            hashMapOf(
                                "name" to name,
                                "email" to email,
                                "followers" to 0,
                                "following" to 0,
                                "postCount" to 0,
                                "uuid" to uid,
                                "photoUri" to null,
                            )
                        )
                        .addOnSuccessListener { callback.onSuccess() }
                        .addOnFailureListener { exception ->
                            callback.onFailure(exception.message ?: "Erro interno no server")
                        }
                        .addOnCompleteListener { callback.onComplete() }
                }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno no Servidor")
            }
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null || photoUri.lastPathSegment == null) {
            callback.onFailure("Usuário não encontrado.")
            return
        }

        val storageRef = FirebaseStorage.getInstance().reference
        val imgRef = storageRef
            .child("images/")
            .child(uid)
            .child(photoUri.lastPathSegment!!)

        imgRef.putFile(photoUri)
            .addOnSuccessListener { result ->
                imgRef.downloadUrl
                    .addOnSuccessListener { url ->
                        val usersRef =
                            FirebaseFirestore.getInstance().collection("/users").document(uid)

                        usersRef.get().addOnSuccessListener { document ->
                            val user = document.toObject(User::class.java)
                            val newUser = user?.copy(photoUrl = url.toString())

                            if (newUser != null) {
                                usersRef.set(newUser)
                                    .addOnSuccessListener {
                                        callback.onSuccess()
                                    }
                                    .addOnFailureListener { exception ->
                                        callback.onFailure(
                                            exception.message ?: "Falha ao atualizar a foto."
                                        )
                                    }
                                    .addOnCompleteListener {
                                        callback.onComplete()
                                    }
                            }
                        }
                    }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao subir a foto")
            }
    }
}