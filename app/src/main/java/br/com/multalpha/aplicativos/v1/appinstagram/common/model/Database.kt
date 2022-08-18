package br.com.multalpha.aplicativos.v1.appinstagram.common.model

import java.util.*

/**
 * Created by João Bosco on 02/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()

    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(UUID.randomUUID().toString(), "userA", "userA@gmail.com", "12345678")
        val userB = UserAuth(UUID.randomUUID().toString(), "userB", "userB@gmail.com", "87654321")

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        sessionAuth = usersAuth.first()
    }
    
}