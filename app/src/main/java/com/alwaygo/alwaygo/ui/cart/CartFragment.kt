package com.alwaygo.alwaygo.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.databinding.FragmentCartBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : CoreFragment<FragmentCartBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding?.tabLayout
        val viewPager2 = binding?.viewPager

        val adapter = CartPagerAdapter(this)
        viewPager2?.adapter = adapter

        val tabTitles = arrayOf("Last visited", "Favorites")
        val tabIcons = arrayOf(R.drawable.last_visited_icon, R.drawable.favorite_menu)

        TabLayoutMediator(tabLayout!!, viewPager2!!) { tab, position ->
            tab.text = tabTitles[position]
            tab.icon = ContextCompat.getDrawable(requireContext(), tabIcons[position])
        }.attach()
    }
}