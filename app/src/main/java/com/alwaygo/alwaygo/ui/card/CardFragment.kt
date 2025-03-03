package com.alwaygo.alwaygo.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.databinding.FragmentCardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : CoreFragment<FragmentCardBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}