package br.com.multalpha.aplicativos.v1.appinstagram.ui.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user_list.view.*

/**
 * Created by João Bosco on 24/08/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class SearchAdapter(
    private val itemClick: (String) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var items: List<User> = mutableListOf()

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
        fun bind(user: User) {
            Glide.with(itemView.context).load(user.photoUrl).into(itemView.search_img_user)
            itemView.search_txt_username.text = user.name
            itemView.setOnClickListener {
                if (user.uuid != null)
                    itemClick.invoke(user.uuid)
            }
        }
    }
}