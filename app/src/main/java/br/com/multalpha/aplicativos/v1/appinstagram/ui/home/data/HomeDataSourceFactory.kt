package br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.Cache
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post

/**
 * Created by João Bosco on 27/01/2022.
 */
class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }

    fun createRemoteDataSource(): HomeDataSource {
        return FireHomeDataSource()
    }

    fun createFromFeeds(): HomeDataSource {
        if (feedCache.isCached()) {
            return HomeLocalDataSource(feedCache)
        }
        return FireHomeDataSource()
    }
}