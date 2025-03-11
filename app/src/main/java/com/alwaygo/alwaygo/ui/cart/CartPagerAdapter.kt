package com.alwaygo.alwaygo.ui.cart

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alwaygo.alwaygo.ui.favorite.FavoriteFragment

class CartPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LastVisitedFragment()
            1 -> FavoriteFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
