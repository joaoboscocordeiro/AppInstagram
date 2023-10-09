package br.com.multalpha.aplicativos.v1.appinstagram.ui.home

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post

/**
 * Created by João Bosco on 12/11/2021.
 */
interface Home {

    interface Presenter : BasePresenter {
        fun fetchFeed()
        fun logout()
        fun clear()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }
}