package br.com.multalpha.aplicativos.v1.appinstagram.common.model

/**
 * Created by João Bosco on 26/08/2022.
 */
data class User(
    val uuid: String? = null,
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val postCount: Int = 0,
    val followers: Int = 0,
    val following: Int = 0
)
