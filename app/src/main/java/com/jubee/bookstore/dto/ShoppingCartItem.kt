package com.jubee.bookstore.dto

data class ShoppingCartItem(
    val book: BookDto? = null,
    val count: Int = 0
)