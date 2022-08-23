package br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data

import android.net.Uri
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 08/02/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface AddDataSource {

    fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) { throw UnsupportedOperationException() }

    fun fetchSession(): UserAuth { throw UnsupportedOperationException() }
}