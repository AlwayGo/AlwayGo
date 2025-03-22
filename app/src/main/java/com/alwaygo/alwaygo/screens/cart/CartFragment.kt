package com.alwaygo.alwaygo.screens.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.databinding.FragmentCartBinding
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

        setUpViewPager()
        setUpTabLayout()
    }

    private fun setUpViewPager() {
        binding?.viewPager?.adapter = CartPagerAdapter(this)
    }

    private fun setUpTabLayout() {
        val tabLayout = binding?.tabLayout
        val viewPager2 = binding?.viewPager

        if (tabLayout != null && viewPager2 != null) {
            val tabTitles = arrayOf("Last visited", "For You")
            val tabIcons = arrayOf(R.drawable.last_visited_icon, R.drawable.star_icon)

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = tabTitles[position]
                tab.icon = ContextCompat.getDrawable(requireContext(), tabIcons[position])
            }.attach()

            tabLayout.setTabTextColors(
                ContextCompat.getColor(requireContext(), R.color.text_unselected_color),
                ContextCompat.getColor(requireContext(), R.color.text_selected_color)
            )
            tabLayout.tabIconTint =
                ContextCompat.getColorStateList(requireContext(), R.color.tab_icon_color)

        }
    }

}