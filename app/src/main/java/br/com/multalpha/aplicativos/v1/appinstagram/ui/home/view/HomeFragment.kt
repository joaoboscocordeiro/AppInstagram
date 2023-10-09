package br.com.multalpha.aplicativos.v1.appinstagram.ui.home.view

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseFragment
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.DependencyInjector
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.databinding.FragmentHomeBinding
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.Home
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.presentation.HomePresenter
import br.com.multalpha.aplicativos.v1.appinstagram.ui.login.view.LoginActivity
import br.com.multalpha.aplicativos.v1.appinstagram.ui.main.LogoutListener

/**
 * Created by João Bosco on 24/10/2021.
 */

class HomeFragment : BaseFragment<FragmentHomeBinding, Home.Presenter>(
    R.layout.fragment_home,
    FragmentHomeBinding::bind
), Home.View {

    override lateinit var presenter: Home.Presenter

    private val adapter = FeedAdapter()

    private var logoutListener: LogoutListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LogoutListener) {
            logoutListener = context
        }
    }

    override fun setupViews() {
        binding?.homeRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.homeRv?.adapter = adapter

        presenter.fetchFeed()
    }

    override fun setupPresenter() {
        presenter = HomePresenter(this, DependencyInjector.homeRepository())
    }

    override fun getMenu() = R.menu.menu_profile

    override fun showProgress(enabled: Boolean) {
        binding?.homeProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPosts() {
        binding?.homeTxtEmpty?.visibility = View.VISIBLE
        binding?.homeRv?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.homeTxtEmpty?.visibility = View.GONE
        binding?.homeRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                logoutListener?.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}