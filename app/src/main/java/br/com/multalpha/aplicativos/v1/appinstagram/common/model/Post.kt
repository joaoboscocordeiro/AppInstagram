package br.com.multalpha.aplicativos.v1.appinstagram.common.model

/**
 * Created by João Bosco on 12/11/2021.
 */
data class Post(
    val uuid: String? = null,
    val photoUrl: String? = null,
    val caption: String? = null,
    val timestamp: Long = 0,
    val publisher: User? = null
)
