package com.alwaygo.alwaygo.screens.login_register.register

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.data.remote.model.User
import com.alwaygo.alwaygo.databinding.FragmentRegisterBinding
import com.alwaygo.alwaygo.utils.UserResource
import com.alwaygo.alwaygo.utils.Validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : CoreFragment<FragmentRegisterBinding>() {
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.createAccountBtn?.setOnClickListener {
//            findNavController().navigate(R.id.action_registerFragment_to_homeFragment2)
        }

        binding?.apply {
            // Validate inputs on text change
            editTextUsername.addTextChangedListener(createTextWatcher { username ->
                editTextUsername.error = Validator.isValidUsername(username)
            })

            editTextEmail.addTextChangedListener(createTextWatcher { email ->
                editTextEmail.error = Validator.isValidEmail(email)
            })

            editTextPassword.addTextChangedListener(createTextWatcher { password ->
                val error = Validator.isValidPassword(password)
                editTextPassword.error = error
                if (error == null) {
                    Toast.makeText(requireContext(), "Strong password!", Toast.LENGTH_SHORT).show()
                }
            })

            createAccountBtn.setOnClickListener {
                val username = editTextUsername.text.toString().trim()
                val email = editTextEmail.text.toString().trim()
                val password = editTextPassword.text.toString().trim()
                val isTermsChecked = checkBoxSignUp.isChecked

                // Perform validation
                val usernameError = Validator.isValidUsername(username)
                val emailError = Validator.isValidEmail(email)
                val passwordError = Validator.isValidPassword(password)
                val isCheckboxValid = Validator.isCheckboxChecked(isTermsChecked)

                // Show errors if validation fails
                if (usernameError != null) {
                    editTextUsername.error = usernameError
                    return@setOnClickListener
                }
                if (emailError != null) {
                    editTextEmail.error = emailError
                    return@setOnClickListener
                }
                if (passwordError != null) {
                    editTextPassword.error = passwordError
                    return@setOnClickListener
                }
                if (!isCheckboxValid) {
                    checkBoxSignUp.setTextColor(Color.RED)
                    Toast.makeText(requireContext(), "This must be accepted", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                } else {
                    checkBoxSignUp.setTextColor(Color.BLACK)
                }

                // If all validations pass, proceed
                createAccountBtn.isEnabled = false
                progressBar.visibility = View.VISIBLE

                val user = User(username, email, password)
                viewModel.createAccountEmailAndPassword(user, password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect { user ->
                when (user) {
                    is UserResource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.createAccountBtn?.isEnabled = false
                    }

                    is UserResource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.createAccountBtn?.isEnabled = true
                        Toast.makeText(
                            requireContext(),
                            "Registration Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
//                        findNavController().navigate(R.id.action_registerFragment_to_homeFragment2)
                    }

                    is UserResource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.createAccountBtn?.isEnabled = true
                        Toast.makeText(requireContext(), user.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun createTextWatcher(validationFunction: (String) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validationFunction(s.toString().trim())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }
}