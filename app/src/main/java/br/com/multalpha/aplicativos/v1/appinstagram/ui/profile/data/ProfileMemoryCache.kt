package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.Cache
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User

/**
 * Created by João Bosco on 27/01/2022.
 */
object ProfileMemoryCache : Cache<Pair<User, Boolean?>> {

    private var user: Pair<User, Boolean?>? = null

    override fun isCached(): Boolean {
        return user != null
    }

    override fun get(key: String): Pair<User, Boolean?>? {
        if (user?.first?.uuid == key) {
            return user
        }
        return null
    }

    override fun put(data: Pair<User, Boolean?>?) {
        user = data
    }
}