package com.alwaygo.alwaygo.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.databinding.FragmentFavoriteBinding
import com.alwaygo.alwaygo.screens.adapter.FavoriteTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : CoreFragment<FragmentFavoriteBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager2()
        setUpTabLayout()
    }

    private fun setUpViewPager2() {
        binding?.viewPager?.adapter = FavoriteTabAdapter(this)
    }

    private fun setUpTabLayout() {
        val tabLayout = binding?.tabLayout
        val viewPager2 = binding?.viewPager

        if (tabLayout != null && viewPager2 != null) {
            val tabTitles = arrayOf("All items", "Boards")

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()

            tabLayout.setTabTextColors(
                ContextCompat.getColor(requireContext(), R.color.text_unselected_color),
                ContextCompat.getColor(requireContext(), R.color.text_selected_color)
            )
        }
    }
}