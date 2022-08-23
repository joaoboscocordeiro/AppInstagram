package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.Cache
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 27/01/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
object ProfileMemoryCache : Cache<UserAuth> {

    private var userAuth: UserAuth? = null

    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): UserAuth? {
        if (userAuth?.uuid == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: UserAuth?) {
        userAuth = data
    }

}