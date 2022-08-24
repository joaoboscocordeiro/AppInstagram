package br.com.multalpha.aplicativos.v1.appinstagram.ui.search.view

import android.app.SearchManager
import android.content.Context
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseFragment
import br.com.multalpha.aplicativos.v1.appinstagram.databinding.FragmentSearchBinding
import br.com.multalpha.aplicativos.v1.appinstagram.ui.search.Search
import kotlinx.android.synthetic.main.item_user_list.view.*

/**
 * Created by João Bosco on 24/10/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter>(
    R.layout.fragment_search,
    FragmentSearchBinding::bind
) {

    override lateinit var presenter: Search.Presenter

    override fun setupViews() {
        binding?.searchRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.searchRv?.adapter = PostAdapter()
    }

    override fun setupPresenter() {
        // TODO:
    }

    override fun getMenu() = R.menu.menu_search

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menu.findItem(R.menu.menu_search).actionView
    }

    private class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            return PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
            )
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        override fun getItemCount(): Int {
            return 30
        }

        private class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(image: Int) {

                itemView.search_img_user.setImageResource(image)
            }
        }
    }

}