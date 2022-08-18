package br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.presentation

import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.Splash
import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data.SplashCallback
import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data.SplashRepository

/**
 * Created by João Bosco on 11/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class SplashPresenter(
    private var view: Splash.View?,
    private val repository: SplashRepository
) : Splash.Presenter {

    override fun authenticated() {
        repository.session(object : SplashCallback {
            override fun onSuccess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}