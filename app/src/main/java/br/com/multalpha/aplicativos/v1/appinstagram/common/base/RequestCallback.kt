package br.com.multalpha.aplicativos.v1.appinstagram.common.base

/**
 * Created by João Bosco on 13/11/2021.
 */
interface RequestCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(message: String)
    fun onComplete()
}