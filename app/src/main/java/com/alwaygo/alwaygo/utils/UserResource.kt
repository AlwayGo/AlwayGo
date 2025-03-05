package com.alwaygo.alwaygo.utils

sealed class UserResource<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : UserResource<T>(data)
    class Error<T>(message: String, data: T? = null) : UserResource<T>(null, message)
    class Loading<T> : UserResource<T>()
    class Unspecified<T> : UserResource<T>()

}
