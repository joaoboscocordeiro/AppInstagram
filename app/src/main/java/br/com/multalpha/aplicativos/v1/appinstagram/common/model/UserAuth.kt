package br.com.multalpha.aplicativos.v1.appinstagram.common.model

/**
 * Created by João Bosco on 02/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

data class UserAuth(
    val uuid: String,
    val name: String,
    val email: String,
    val password: String,
    val postCount: Int = 0,
    val followingCount: Int = 0,
    val followersCount: Int = 0
)
