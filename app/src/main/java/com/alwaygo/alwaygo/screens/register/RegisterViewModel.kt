package com.alwaygo.alwaygo.screens.register

import androidx.lifecycle.ViewModel
import com.alwaygo.alwaygo.data.remote.model.User
import com.alwaygo.alwaygo.utils.UserResource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    private val _register = MutableStateFlow<UserResource<FirebaseUser>>(UserResource.Unspecified())
    val register: MutableStateFlow<UserResource<FirebaseUser>> = _register

    fun createAccountEmailAndPassword(user: User, password: String) {
        runBlocking {
            _register.emit(UserResource.Loading())
        }
        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener {
                it.user?.let {firebaseUser ->
                    _register.value = UserResource.Success(firebaseUser)
                }
            }.addOnFailureListener {
                _register.value = UserResource.Error(it.message.toString())
            }
    }
}