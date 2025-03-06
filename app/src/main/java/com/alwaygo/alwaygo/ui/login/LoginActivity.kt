package com.alwaygo.alwaygo.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.databinding.ActivityLoginBinding
import com.alwaygo.alwaygo.ui.signup.RegisterActivity
import com.alwaygo.alwaygo.utils.UserResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Status bar rəngini dəyiş
        window.statusBarColor = getColor(R.color.white)

        binding?.apply {
            loginBtn.setOnClickListener {
                val email = editTextUsername.text.toString()
                val password = editTextPassword.text.toString()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this@LoginActivity, "Email və ya şifrə boş ola bilməz", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Button-u deaktiv et və loading göstəricisini aktiv et
                loginBtn.isEnabled = false
                progressBar.visibility = View.VISIBLE

                viewModel.loginUser(email, password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.login.collect {
                when (it) {
                    is UserResource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.loginBtn?.isEnabled = false
                    }

                    is UserResource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.loginBtn?.isEnabled = true
                        Toast.makeText(this@LoginActivity, "Giriş uğurlu oldu", Toast.LENGTH_SHORT).show()
                        // Burada ana ekrana yönləndirmə edə bilərsən
                    }

                    is UserResource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.loginBtn?.isEnabled = true
                        Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }
        setSignUpTextClickable()
    }

    private fun setSignUpTextClickable() {
        val text = "Already have an account? Sign Up"

        val spannableString = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        val startIndex = text.indexOf("Sign Up")
        spannableString.setSpan(clickableSpan, startIndex, startIndex + 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding?.signUpText?.text = spannableString
        binding?.signUpText?.movementMethod = LinkMovementMethod.getInstance()
    }
}