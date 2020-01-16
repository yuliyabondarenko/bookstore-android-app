package com.jubee.bookstore.dto

data class ShoppingCartItem(
    var book: BookDto,
    var count: Int = 0
)