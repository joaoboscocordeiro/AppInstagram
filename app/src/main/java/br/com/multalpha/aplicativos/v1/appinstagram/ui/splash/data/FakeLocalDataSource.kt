package br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Database

/**
 * Created by João Bosco on 11/11/2021.
 */

class FakeLocalDataSource : SplashDataSource {
    override fun session(callback: SplashCallback) {
        if (Database.sessionAuth != null) {
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}