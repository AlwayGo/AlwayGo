package com.alwaygo.alwaygo.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.databinding.ActivitySignUpBinding
import com.alwaygo.alwaygo.extensions.setStatusBarColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // statusBar color
        setStatusBarColors(R.color.white)
    }
}