package br.com.multalpha.aplicativos.v1.appinstagram.ui

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
import br.com.multalpha.aplicativos.v1.appinstagram.ui.add.view.AddFragment
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.view.HomeFragment
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.view.ProfileFragment
import br.com.multalpha.aplicativos.v1.appinstagram.ui.search.SearchFragment
import br.com.multalpha.aplicativos.v1.appinstagram.util.replaceFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var addFragment: Fragment
    private lateinit var profileFragment: Fragment
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

            //currentFragment = homeFragment

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
            }
            R.id.menu_bottom_add -> {
                if (currentFragment == addFragment) return false
                currentFragment = addFragment
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

}