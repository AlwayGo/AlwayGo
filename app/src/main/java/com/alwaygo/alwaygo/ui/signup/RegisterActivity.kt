package com.alwaygo.alwaygo.ui.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.data.remote.model.User
import com.alwaygo.alwaygo.databinding.ActivityRegisterBinding
import com.alwaygo.alwaygo.extensions.setStatusBarColors
import com.alwaygo.alwaygo.ui.login.LoginActivity
import com.alwaygo.alwaygo.utils.UserResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private var binding: ActivityRegisterBinding? = null
    private val viewModel: RegisterViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // statusBar color
        setStatusBarColors(R.color.white)

        binding?.apply {
            createAccountBtn.setOnClickListener {
                // disable button
                createAccountBtn.isEnabled = false
                createAccountBtn.text = ""

                // show progress bar
                progressBar.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    progressBar.visibility = View.GONE
                    createAccountBtn.text = "Create Account"
                    createAccountBtn.isEnabled = true

                    Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_SHORT).show()
                }, 2000)

                val user = User(
                    username = editTextUsername.text.toString(),
                    email = editTextEmail.text.toString(),
                    password = editTextPassword.text.toString()
                )
                viewModel.createAccountEmailAndPassword(user, editTextPassword.text.toString())
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when (it) {
                    is UserResource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.createAccountBtn?.isEnabled = false
                    }

                    is UserResource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.createAccountBtn?.text = "Create Account"
                        binding?.createAccountBtn?.isEnabled = true
                        Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_SHORT).show()
                    }

                    is UserResource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.createAccountBtn?.text = "Create Account"
                        binding?.createAccountBtn?.isEnabled = true
                        Toast.makeText(this@RegisterActivity, it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }
        setLoginTextClickable()
    }

    private fun setLoginTextClickable() {
        val text = "Already have an account? Log in"

        val spannableString = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        val startIndex = text.indexOf("Log in")
        spannableString.setSpan(clickableSpan, startIndex, startIndex + 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding?.loginText?.text = spannableString
        binding?.loginText?.movementMethod = LinkMovementMethod.getInstance()
    }
}