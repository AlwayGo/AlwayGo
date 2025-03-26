package com.alwaygo.alwaygo.screens.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alwaygo.alwaygo.screens.cart.foryou.ForYouFragment
import com.alwaygo.alwaygo.screens.cart.lastvisited.LastVisitedFragment

class CartPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LastVisitedFragment()
            1 -> ForYouFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
