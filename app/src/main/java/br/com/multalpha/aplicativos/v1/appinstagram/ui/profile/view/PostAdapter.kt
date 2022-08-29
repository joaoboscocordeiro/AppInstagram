package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_profile_grid.view.*

/**
 * Created by João Bosco on 12/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var items: List<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position].photoUrl)
    }

    override fun getItemCount(): Int = items.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photoUrl: String?) {
            Glide.with(itemView.context).load(photoUrl).into(itemView.item_profile_img_grid)
        }
    }
}