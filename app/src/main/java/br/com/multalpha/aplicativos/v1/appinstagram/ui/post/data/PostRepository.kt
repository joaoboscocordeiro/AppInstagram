package br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data

/**
 * Created by João Bosco on 23/08/2022.
 */
class PostRepository(
    private val dataSource: PostDataSource
) {
    suspend fun fetchPictures() = dataSource.fetchPictures()
}