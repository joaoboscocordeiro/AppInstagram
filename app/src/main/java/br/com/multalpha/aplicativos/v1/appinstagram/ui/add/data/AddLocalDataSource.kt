package br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data

import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Database
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth

/**
 * Created by João Bosco on 08/02/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!")
    }
}