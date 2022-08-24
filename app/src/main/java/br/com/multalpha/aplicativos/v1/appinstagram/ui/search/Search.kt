package br.com.multalpha.aplicativos.v1.appinstagram.ui.search

import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BasePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseView

/**
 * Created by João Bosco on 24/08/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface Search {

    interface Presenter : BasePresenter {}

    interface View : BaseView<Presenter> {}
}