package br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data

import android.net.Uri

/**
 * Created by João Bosco on 23/08/2022.
 */
interface PostDataSource {
    suspend fun fetchPictures(): List<Uri>
}