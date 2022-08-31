package br.com.multalpha.aplicativos.v1.appinstagram.ui.search .data

import android.os.Handler
import android.os.Looper
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User

/**
 * Created by João Bosco on 13/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class SearchFakeRemoteDataSource : SearchDataSource {
    override fun fetchUsers(name: String, callback: RequestCallback<List<User>>) {
        Handler(Looper.getMainLooper()).postDelayed({
//            val users = Database.usersAuth.filter {
//                it.name?.lowercase()
//                    ?.startsWith(name.lowercase()) ?: && it.uuid != Database.sessionAuth!!.uuid
//            }
//            callback.onSuccess(users.toList())
//            callback.onComplete()
        }, 2000)
    }
}