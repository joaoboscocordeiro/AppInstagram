package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.view

/**
 * Created by João Bosco on 06/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface FragmentAttachListener {
    fun goToNameAndPasswordScreen(email: String)
    fun goToWelcomeScreen(name: String)
    fun goToPhotoScreen()
    fun goToMainScreen()
    fun goToGalleryScreen()
    fun goToCameraScreen()
}