package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import android.os.Handler
import android.os.Looper
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Database
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 13/11/2021.
 */
class ProfileFakeRemoteDataSource : ProfileDataSource {

    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { userUUID == it.uuid }
            if (userAuth != null) {
                if (userAuth == Database.sessionAuth) {
                    callback.onSuccess(Pair(userAuth, null))
                } else {
                    val followings = Database.followers[Database.sessionAuth!!.uuid]

                    val destUser = followings?.firstOrNull { it == userUUID }

                    callback.onSuccess(Pair(userAuth, destUser != null))
                }
            } else {
                callback.onFailure(R.string.profile_failure.toString())
            }
            callback.onComplete()
        }, 2000)
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val posts = Database.posts[userUUID]
            callback.onSuccess(posts?.toList() ?: emptyList())
            callback.onComplete()
        }, 2000)
    }

    override fun followUser(userUUID: String, isFollow: Boolean, callback: RequestCallback<Boolean>) {
        Handler(Looper.getMainLooper()).postDelayed({
            var followers = Database.followers[Database.sessionAuth!!.uuid]
            if (followers == null) {
                followers = mutableSetOf()
                Database.followers[Database.sessionAuth!!.uuid] = followers
            }
            if (isFollow) {
                Database.followers[Database.sessionAuth!!.uuid]!!.add(userUUID)
            } else {
                Database.followers[Database.sessionAuth!!.uuid]!!.remove(userUUID)
            }
            callback.onSuccess(true)
            callback.onComplete()
        }, 500)
    }
}