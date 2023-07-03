package br.com.multalpha.aplicativos.v1.appinstagram.ui.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.UserAuth
import kotlinx.android.synthetic.main.item_user_list.view.*

/**
 * Created by João Bosco on 24/08/2022.
 */
class SearchAdapter(
    private val itemClick: (String) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var items: List<UserAuth> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserAuth) {
            itemView.search_img_user.setImageURI(user.photoUri)
            itemView.search_txt_username.text = user.name
            itemView.setOnClickListener {
                itemClick.invoke(user.uuid)
            }
        }
    }
}