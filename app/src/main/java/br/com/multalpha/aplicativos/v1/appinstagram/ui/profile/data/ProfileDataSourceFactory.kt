package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.Cache
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User

/**
 * Created by João Bosco on 27/01/2022.
 */
class ProfileDataSourceFactory(
    private val profileCache: Cache<Pair<User, Boolean?>>,
    private val postsCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createRemoteDataSource(): ProfileDataSource {
        return FireProfileDataSource()
    }

    fun createFromUser(uuid: String?): ProfileDataSource {
        if (uuid != null)
            return createRemoteDataSource()

        if (profileCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return createRemoteDataSource()
    }

    fun createFromPosts(uuid: String?): ProfileDataSource {
        if (uuid != null)
            return createRemoteDataSource()
        
        if (postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return createRemoteDataSource()
    }
}