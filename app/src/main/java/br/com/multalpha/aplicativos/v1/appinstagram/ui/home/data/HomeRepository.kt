package br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post

/**
 * Created by João Bosco on 13/11/2021.
 */

class HomeRepository(private val dataSourceFactory: HomeDataSourceFactory) {
    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putFeed(null)
    }

    fun fetUserFeeds(callback: RequestCallback<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userId = localDataSource.fetchSession()

        val dataSource = dataSourceFactory.createFromFeeds()

        dataSource.fetchFeed(userId, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putFeed(data)
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

    fun logout() {
        dataSourceFactory.createRemoteDataSource().logout()
    }
}