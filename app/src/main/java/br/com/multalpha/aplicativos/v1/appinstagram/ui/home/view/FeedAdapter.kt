package br.com.multalpha.aplicativos.v1.appinstagram.ui.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import kotlinx.android.synthetic.main.item_post_list.view.*

/**
 * Created by João Bosco on 28/01/2022.
 */
class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    var items: List<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FeedAdapter.FeedViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.home_img_post.setImageURI(post.uri)
            itemView.home_img_user.setImageURI(post.publisher.photoUri)
            itemView.home_txt_caption.text = post.caption
            itemView.home_txt_username.text = post.publisher.name
        }
    }
}