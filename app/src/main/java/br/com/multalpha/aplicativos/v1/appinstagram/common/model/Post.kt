package br.com.multalpha.aplicativos.v1.appinstagram.common.model

import android.net.Uri

/**
 * Created by João Bosco on 12/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
data class Post(
    val uuid: String,
    val uri: Uri,
    val caption: String,
    val timestamp: Long,
    val publisher: UserAuth
)
