package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 13/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>)
    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession(): UserAuth { throw UnsupportedOperationException() }
    fun putUser(response: UserAuth) { throw UnsupportedOperationException() }
    fun putPosts(response: List<Post>?) { throw UnsupportedOperationException() }

}