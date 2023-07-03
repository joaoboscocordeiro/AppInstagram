package br.com.multalpha.aplicativos.v1.appinstagram.ui.post

import android.net.Uri
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView

/**
 * Created by João Bosco on 23/08/2022.
 */
interface Post {

    interface Presenter : BasePresenter {
        fun selectUri(uri: Uri)
        fun getSelectedUri(): Uri?
        fun fetchPictures()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayFullPictures(posts: List<Uri>)
        fun displayEmptyPictures()
        fun displayRequestFailure(message: String)
    }
}