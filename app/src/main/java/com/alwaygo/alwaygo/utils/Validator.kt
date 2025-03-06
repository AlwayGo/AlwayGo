package com.alwaygo.alwaygo.utils

import android.util.Patterns

object Validator {

    fun isValidUsername(username: String): String? {
        return when {
            username.length < 8 -> "Username must be exactly 8 characters"
            username.length > 8 -> "The number of characters is too many"
            else -> null
        }
    }

    fun isValidEmail(email: String): String? {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Wrong email format"
        } else {
            null
        }
    }

    fun isValidPassword(password: String): String? {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$".toRegex()

        return when {
            password.length < 8 -> "Password must be at least 8 characters"
            !passwordPattern.matches(password) -> "Use uppercase, lowercase, number, and special character"
            else -> null
        }
    }

    fun isCheckboxChecked(isChecked: Boolean): Boolean {
        return isChecked
    }
}