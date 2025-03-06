package com.alwaygo.alwaygo.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

        binding?.loginBtn?.setOnClickListener {
            val email = binding?.editTextEmail?.text.toString()
            val password = binding?.editTextPassword?.text.toString()

            binding?.loginBtn?.isEnabled = false
            binding?.loginBtn?.text = ""
            binding?.progressBar?.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                binding?.progressBar?.visibility = View.GONE
                binding?.loginBtn?.text = getString(R.string.log_in)
                binding?.loginBtn?.isEnabled = true
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            }, 2000)

            viewModel.loginUser(email, password)
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
                        binding?.loginBtn?.text = getString(R.string.log_in)
                        binding?.loginBtn?.isEnabled = true
                        // Navigate to the next screen after successful login
                        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    }
                    is UserResource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.loginBtn?.text = getString(R.string.log_in)
                        binding?.loginBtn?.isEnabled = true
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    //        setRegisterTextClickable()
    }

//    private fun setRegisterTextClickable() {
//        val text = getString(R.string.login_signup_text)
//
//        val spannableString = SpannableString(text)
//        val clickableSpan = object : ClickableSpan() {
//            override fun onClick(widget: View) {
//                // Navigate to RegisterFragment when clicked
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, RegisterFragment())
//                    .addToBackStack(null)
//                    .commit()
//            }
//        }
//
//        val startIndex = text.indexOf("Register")
//        spannableString.setSpan(clickableSpan, startIndex, startIndex + 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        binding?.signUpText?.text = spannableString
//        binding?.signUpText?.movementMethod = LinkMovementMethod.getInstance()
//    }
}
