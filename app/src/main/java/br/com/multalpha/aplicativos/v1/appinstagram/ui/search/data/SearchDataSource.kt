package br.com.multalpha.aplicativos.v1.appinstagram.ui.search.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 24/08/2022.
 */
interface SearchDataSource {
    fun fetchUsers(name: String, callback: RequestCallback<List<UserAuth>>)
}