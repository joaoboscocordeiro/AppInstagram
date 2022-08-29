package br.com.multalpha.aplicativos.v1.appinstagram.common.model

import android.net.Uri
import java.io.File
import java.util.*

/**
 * Created by João Bosco on 02/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

object Database {

    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, MutableSet<String>>()

    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(
            UUID.randomUUID().toString(), "usjoaoerA", "userA@gmail.com", "12345678",
            Uri.fromFile(File("/storage/self/primary/Android/media/br.com.multalpha.aplicativos.v1.appinstagram/AppInstagram/2022-08-22-17-19-41-642.jpg"))
        )
        val userB = UserAuth(
            UUID.randomUUID().toString(), "userB", "userB@gmail.com", "87654321",
            Uri.fromFile(File("/storage/self/primary/Android/media/br.com.multalpha.aplicativos.v1.appinstagram/AppInstagram/2022-08-22-17-20-02-493.jpg"))
        )

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        for (i in 0..30) {
            val user = UserAuth(UUID.randomUUID().toString(), "User$i", "user$i@gmail.com", "12345678", null)
            usersAuth.add(user)
        }

        //sessionAuth = usersAuth.first()

        //followers[sessionAuth!!.uuid]?.add(usersAuth[2].uuid)
    }

}