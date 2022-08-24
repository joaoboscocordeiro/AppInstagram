package br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data

/**
 * Created by João Bosco on 23/08/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class PostRepository(
    private val dataSource: PostDataSource
) {
    suspend fun fetchPictures() = dataSource.fetchPictures()
}