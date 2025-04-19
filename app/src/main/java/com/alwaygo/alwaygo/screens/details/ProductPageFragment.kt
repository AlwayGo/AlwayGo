package com.alwaygo.alwaygo.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.databinding.FragmentProductPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductPageFragment : CoreFragment<FragmentProductPageBinding>() {

    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductPageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding?.reviewsRecycler?.apply {
            reviewAdapter = ReviewAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}