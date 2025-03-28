package com.alwaygo.alwaygo.data.remote.model


//Login
data class User(
    var username: String,
    var email: String,
    var password: String,
) {

    constructor() : this("", "", "")
}

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
)

data class Category(
    val id: Int,
    val name: String,
)


