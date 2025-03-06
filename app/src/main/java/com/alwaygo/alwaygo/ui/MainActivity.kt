package com.alwaygo.alwaygo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.databinding.ActivityMainBinding
import com.alwaygo.alwaygo.extensions.setStatusBarColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarColors(R.color.white)

    }
}