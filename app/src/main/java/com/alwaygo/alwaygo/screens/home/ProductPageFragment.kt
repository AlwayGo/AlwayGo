package com.alwaygo.alwaygo.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.databinding.FragmentProductPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductPageFragment : CoreFragment<FragmentProductPageBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductPageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}