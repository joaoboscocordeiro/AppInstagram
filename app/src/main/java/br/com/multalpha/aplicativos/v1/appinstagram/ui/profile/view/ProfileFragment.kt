package br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.view

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.BaseFragment
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.DependencyInjector
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.Post
import br.com.multalpha.aplicativos.v1.appinstagram.common.model.User
import br.com.multalpha.aplicativos.v1.appinstagram.databinding.FragmentProfileBinding
import br.com.multalpha.aplicativos.v1.appinstagram.ui.main.LogoutListener
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.Profile
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.presentation.ProfilePresenter
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by João Bosco on 24/10/2021.
 */

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    override lateinit var presenter: Profile.Presenter
    private val adapter = PostAdapter()
    private var uuid: String? = null

    private var logoutListener: LogoutListener? = null
    private var followListener: FollowListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LogoutListener) {
            logoutListener = context
        }
        if (context is FollowListener) {
            followListener = context
        }
    }

    override fun setupPresenter() {
        presenter = ProfilePresenter(this, DependencyInjector.profileRepository())
    }

    override fun setupViews() {
        uuid = arguments?.getString(KEY_USER_ID)
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = adapter
        binding?.profileNavTabs?.setOnNavigationItemSelectedListener(this)

        binding?.profileBtnEditProfile?.setOnClickListener {
            if (it.tag == true) {
                binding?.profileBtnEditProfile?.text = getString(R.string.follow)
                binding?.profileBtnEditProfile?.tag = false
                presenter.followUser(uuid, false)
            } else if (it.tag == false) {
                binding?.profileBtnEditProfile?.text = getString(R.string.unfollow)
                binding?.profileBtnEditProfile?.tag = true
                presenter.followUser(uuid, true)
            }
        }

        presenter.fetchUserProfile(uuid)
    }

    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility =
            if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(user: Pair<User, Boolean?>) {
        val (userAuth, following) = user

        binding?.profileTxtPostCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.following.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followers.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"

        binding?.let {
            Glide.with(requireContext()).load(userAuth.photoUrl).into(it.profileImgIcon)
        }

        binding?.profileBtnEditProfile?.text = when (following) {
            null -> getString(R.string.edit_profile)
            true -> getString(R.string.unfollow)
            false -> getString(R.string.follow)
        }
        binding?.profileBtnEditProfile?.tag = following

        presenter.fetchUserPosts(uuid)
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPosts() {
        binding?.profileTxtEmpty?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.profileTxtEmpty?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override fun followUpdated() {
        followListener?.followUpdated()
    }

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile_grid -> {
                binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
            }
            R.id.menu_profile_list -> {
                binding?.profileRv?.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return true
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

    interface FollowListener {
        fun followUpdated()
    }

    companion object {
        const val KEY_USER_ID = "key_user_id"
    }
}