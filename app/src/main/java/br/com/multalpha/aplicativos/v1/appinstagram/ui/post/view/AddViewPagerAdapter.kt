package br.com.multalpha.aplicativos.v1.appinstagram.ui.post.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.multalpha.aplicativos.v1.appinstagram.R

/**
 * Created by João Bosco on 31/01/2022.
 */
class AddViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    val tabs = arrayOf(R.string.photo, R.string.gallery)

    override fun getItemCount() = tabs.size

    override fun createFragment(position: Int): Fragment {
        return when (tabs[position]) {
            R.string.photo -> CameraFragment()
            R.string.gallery -> GalleryFragment()
            else -> throw IllegalArgumentException("Fragmento nao encontrado.")
        }
    }
}