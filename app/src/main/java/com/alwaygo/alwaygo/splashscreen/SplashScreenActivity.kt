package com.alwaygo.alwaygo.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.databinding.ActivitySplashScreenBinding
import com.alwaygo.alwaygo.extensions.setStatusBarColors
import com.alwaygo.alwaygo.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // statusBar color
        setStatusBarColors(R.color.white)

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        val screens = listOf(
            R.layout.splash_screen_1,
            R.layout.splash_screen_2
        )

        val adapter = SplashAdapter(screens)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.setPageTransformer(null)

        viewModel.screenIndex.observe(this) { index ->
            binding.viewPager.currentItem = index
            if (index == 2) {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, 1000)
            }
        }
    }
}