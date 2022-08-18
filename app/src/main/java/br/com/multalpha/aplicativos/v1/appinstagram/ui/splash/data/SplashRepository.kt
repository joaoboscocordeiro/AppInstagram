package br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data

/**
 * Created by João Bosco on 11/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class SplashRepository(private val dataSource: SplashDataSource) {
    fun session(callback: SplashCallback) {
        dataSource.session(callback)
    }
}