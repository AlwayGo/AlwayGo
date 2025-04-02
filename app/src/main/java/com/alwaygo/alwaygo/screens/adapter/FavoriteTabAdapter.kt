package com.alwaygo.alwaygo.screens.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alwaygo.alwaygo.screens.favorite.all_items.AllItemsFragment
import com.alwaygo.alwaygo.screens.favorite.boards.BoardsFragment

class FavoriteTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllItemsFragment()
            1 -> BoardsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
//            else -> AllItemsFragment()
        }
    }
}