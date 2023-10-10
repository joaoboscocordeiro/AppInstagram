package br.com.multalpha.aplicativos.v1.appinstagram.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.databinding.ActivityMainBinding
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.view.HomeFragment
import br.com.multalpha.aplicativos.v1.appinstagram.ui.post.view.AddFragment
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.view.ProfileFragment
import br.com.multalpha.aplicativos.v1.appinstagram.ui.search.view.SearchFragment
import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.view.SplashActivity
import br.com.multalpha.aplicativos.v1.appinstagram.util.replaceFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    AddFragment.AddListener,
    SearchFragment.SearchListener,
    ProfileFragment.FollowListener,
    LogoutListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: Fragment
    private lateinit var addFragment: Fragment
    private lateinit var profileFragment: ProfileFragment
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_insta_camera)
            setDisplayHomeAsUpEnabled(true)
            title = ""

            homeFragment = HomeFragment()
            searchFragment = SearchFragment()
            addFragment = AddFragment()
            profileFragment = ProfileFragment()

            binding.mainBottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
            binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
        }
    }

    private fun setScrollToolbarEnabled(enabled: Boolean) {
        val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.mainAppBar.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled) {
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.mainAppBar.layoutParams = coordinatorParams
    }

    override fun goToProfile(uuid: String) {
        val fragment = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString(ProfileFragment.KEY_USER_ID, uuid)
            }
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment, fragment, fragment.javaClass.simpleName + "detail")
            addToBackStack(null)
            commit()
        }
    }

    override fun followUpdated() {
        homeFragment.presenter.clear()

        if (supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null) {
            profileFragment.presenter.clear()
        }
    }

    override fun logout() {
        if (supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null) {
            profileFragment.presenter.clear()
        }

        homeFragment.presenter.clear()
        homeFragment.presenter.logout()

        val intent = Intent(baseContext, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                if (currentFragment == homeFragment) return false
                currentFragment = homeFragment
            }

            R.id.menu_bottom_search -> {
                if (currentFragment == searchFragment) return false
                currentFragment = searchFragment
                scrollToolbarEnabled = false
            }

            R.id.menu_bottom_add -> {
                if (currentFragment == addFragment) return false
                currentFragment = addFragment
                scrollToolbarEnabled = false
            }

            R.id.menu_bottom_profile -> {
                if (currentFragment == profileFragment) return false
                currentFragment = profileFragment
                scrollToolbarEnabled = true
            }
        }
        setScrollToolbarEnabled(scrollToolbarEnabled)
        currentFragment?.let { fragment ->
            replaceFragment(R.id.main_fragment, fragment)
        }
        return true
    }

    override fun onPostCreated() {
        homeFragment.presenter.clear()

        if (supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null) {
            profileFragment.presenter.clear()
        }

        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
    }
}