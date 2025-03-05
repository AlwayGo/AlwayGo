package com.alwaygo.alwaygo.data.remote.model

data class User(
    var username: String,
    var email: String,
    var password: String
){

    constructor(): this("", "", "")
}
