package br.com.multalpha.aplicativos.v1.appinstagram.ui.post.presentation

import android.net.Uri
import br.com.multalpha.aplicativos.v1.appinstagram.ui.post.Post
import br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data.PostRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by João Bosco on 23/08/2022.
 */
class PostPresenter(
    private var view: Post.View?,
    private val repository: PostRepository
) : Post.Presenter, CoroutineScope {

    private var uri: Uri? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun fetchPictures() {
        // AQUI acontece a chamada na thread MAIN ( UI )
        view?.showProgress(true)
        launch {
            // AQUI dentro do launch, acontece a chamada paralela ( coroutina IO )
            val pictures = repository.fetchPictures()

            withContext(Dispatchers.Main) {
                // AQUI DENTRO executa de volta na MainThread
                if (pictures.isEmpty()) {
                    view?.displayEmptyPictures()
                } else {
                    view?.displayFullPictures(pictures)
                }
                view?.showProgress(false)
            }
        }
    }

    override fun selectUri(uri: Uri) {
        this.uri = uri
    }

    override fun getSelectedUri(): Uri? {
        return uri
    }

    override fun onDestroy() {
        job.cancel()
        view = null
    }
}