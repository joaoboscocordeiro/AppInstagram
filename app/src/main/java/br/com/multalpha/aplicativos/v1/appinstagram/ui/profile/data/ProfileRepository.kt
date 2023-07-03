package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 13/11/2021.
 */
class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }

    fun fetchUserProfile(uuid: String?, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userId = uuid ?: localDataSource.fetchSession().uuid
        val dataSource = dataSourceFactory.createFromUser(userId)

        dataSource.fetchUserProfile(userId, object : RequestCallback<Pair<UserAuth, Boolean?>> {
            override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
                if (uuid == null) {
                    localDataSource.putUser(data)
                }
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }

    fun fetUserPosts(uuid: String?, callback: RequestCallback<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userId = uuid ?: localDataSource.fetchSession().uuid
        val dataSource = dataSourceFactory.createFromPosts(uuid)

        dataSource.fetchUserPosts(userId, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (uuid == null) {
                    localDataSource.putPosts(data)
                }
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }

    fun followUser(uuid: String?, follow: Boolean, callback: RequestCallback<Boolean>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userId = uuid ?: localDataSource.fetchSession().uuid
        val dataSource = dataSourceFactory.createRemoteDataSource()

        dataSource.followUser(userId, follow, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }
}