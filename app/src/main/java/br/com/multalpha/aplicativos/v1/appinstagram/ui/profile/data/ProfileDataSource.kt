package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User

/**
 * Created by João Bosco on 13/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<User, Boolean?>>)
    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)
    fun followUser(userUUID: String, isFollow: Boolean, callback: RequestCallback<Boolean>) { throw UnsupportedOperationException() }
    fun fetchSession(): String { throw UnsupportedOperationException() }
    fun putUser(response: Pair<User, Boolean?>) { throw UnsupportedOperationException() }
    fun putPosts(response: List<Post>?) { throw UnsupportedOperationException() }

}