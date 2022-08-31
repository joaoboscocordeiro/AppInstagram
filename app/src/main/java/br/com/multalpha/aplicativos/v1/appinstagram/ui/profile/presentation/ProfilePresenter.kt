package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.presentation

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.RequestCallback
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.Profile
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.ProfileRepository

/**
 * Created by João Bosco on 13/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override fun clear() {
        repository.clearCache()
    }

    override fun fetchUserProfile(uuid: String?) {
        view?.showProgress(true)
        repository.fetchUserProfile(uuid, object : RequestCallback<Pair<User, Boolean?>> {
            override fun onSuccess(data: Pair<User, Boolean?>) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {}
        })
    }

    override fun fetchUserPosts(uuid: String?) {
        repository.fetUserPosts(uuid, object : RequestCallback<List<Post>> {
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

    override fun followUser(uuid: String?, follow: Boolean) {
        repository.followUser(uuid, follow, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean) {}

            override fun onFailure(message: String) {}

            override fun onComplete() {}
        })
    }

    override fun onDestroy() {
        view = null
    }

}