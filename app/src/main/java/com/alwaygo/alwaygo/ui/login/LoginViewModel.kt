package com.alwaygo.alwaygo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alwaygo.alwaygo.utils.UserResource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _login = MutableStateFlow<UserResource<Unit>>(UserResource.Unspecified())
    val login: StateFlow<UserResource<Unit>> get() = _login

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _login.value = UserResource.Loading()
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _login.value = UserResource.Success(Unit)
                }
                .addOnFailureListener {
                    _login.value = UserResource.Error(it.message ?: "Giriş zamanı xəta baş verdi")
                }
        }
    }
}
