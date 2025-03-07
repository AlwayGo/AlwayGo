package com.alwaygo.alwaygo.ui.login

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.databinding.FragmentLoginBinding
import com.alwaygo.alwaygo.utils.UserResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : CoreFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            loginBtn.setOnClickListener {
                val email = editTextEmail.text.toString().trim()
                val password = editTextPassword.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                progressBar.visibility = View.VISIBLE
                loginBtn.isEnabled = false

                viewModel.loginUser(email, password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.login.collect { state ->
                when (state) {
                    is UserResource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.progressBar?.isEnabled = false
                    }
                    is UserResource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.loginBtn?.isEnabled = true
                        Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    is UserResource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.loginBtn?.isEnabled = true
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
        setLoginToSignUpBtn()
    }

    private fun setLoginToSignUpBtn() {
        val text = getString(R.string.login_signup_text)
        val spannableString = SpannableString(text)
        val start = text.indexOf("Sign Up")
        val end = start + "Sign Up".length
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding?.signUpText?.text = spannableString
        binding?.signUpText?.movementMethod = LinkMovementMethod.getInstance()
    }
}
