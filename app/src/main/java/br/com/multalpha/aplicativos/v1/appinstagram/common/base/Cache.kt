package br.com.multalpha.aplicativos.v1.appinstagram.common.base

/**
 * Created by João Bosco on 27/01/2022.
 * e-mail - Support: ti.junior@gmail.com
 */

interface Cache<T> {
    fun isCached(): Boolean
    fun get(key: String): T?
    fun put(data: T?)
}