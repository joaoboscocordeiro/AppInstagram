package br.com.multalpha.aplicativos.v1.appinstagram.ui.search

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User

/**
 * Created by João Bosco on 24/08/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface Search {

    interface Presenter : BasePresenter {
        fun fetchUsers(name: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)
        fun displayFullUsers(users: List<User>)
        fun displayEmptyUsers()
    }
}