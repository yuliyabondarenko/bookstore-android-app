package com.jubee.bookstore.model


data class BookModel(
    val id: Long = 0,
    var name: String,
    var image: String? = null,
    var absent: Boolean = false,
    var price: Double
)