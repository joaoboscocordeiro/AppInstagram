package br.com.multalpha.aplicativos.v1.appinstagram.ui.search.presentation

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User
import br.com.multalpha.aplicativos.v1.appinstagram.ui.search.Search
import br.com.multalpha.aplicativos.v1.appinstagram.ui.search.data.SearchRepository

/**
 * Created by João Bosco on 24/08/2022.
 */

class SearchPresenter(
    private var view: Search.View?,
    private val repository: SearchRepository
) : Search.Presenter {

    override fun fetchUsers(name: String) {
        view?.showProgress(true)
        repository.fetUsers(name, object : RequestCallback<List<User>> {
            override fun onSuccess(data: List<User>) {
                if (data.isEmpty()) {
                    view?.displayEmptyUsers()
                } else {
                    view?.displayFullUsers(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayEmptyUsers()
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