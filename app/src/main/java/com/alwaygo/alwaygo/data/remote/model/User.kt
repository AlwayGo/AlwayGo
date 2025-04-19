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

data class FavoriteItem(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val timeAdded: Long // Timestamp when item was added
    // Other properties
)


