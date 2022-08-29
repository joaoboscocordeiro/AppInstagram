package br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by João Bosco on 29/08/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class FireSplashDataSource : SplashDataSource {
    override fun session(callback: SplashCallback) {
        if (FirebaseAuth.getInstance().uid != null) {
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}