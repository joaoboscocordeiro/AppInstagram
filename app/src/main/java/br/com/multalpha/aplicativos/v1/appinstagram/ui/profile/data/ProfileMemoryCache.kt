package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.Cache
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 27/01/2022.
 */
object ProfileMemoryCache : Cache<Pair<UserAuth, Boolean?>> {

    private var userAuth: Pair<UserAuth, Boolean?>? = null

    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): Pair<UserAuth, Boolean?>? {
        if (userAuth?.first?.uuid == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<UserAuth, Boolean?>?) {
        userAuth = data
    }
}