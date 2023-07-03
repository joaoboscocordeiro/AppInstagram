package br.com.multalpha.aplicativos.v1.appinstagram.ui.home.presentation

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.Home
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data.HomeRepository

/**
 * Created by João Bosco on 28/01/2022.
 */

class HomePresenter(
    private var view: Home.View?,
    private val repository: HomeRepository
) : Home.Presenter {

    override fun clear() {
        repository.clearCache()
    }

    override fun fetchFeed() {
        view?.showProgress(true)
        repository.fetUserFeeds(object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPosts()
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}