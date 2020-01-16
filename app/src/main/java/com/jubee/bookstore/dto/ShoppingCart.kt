package com.jubee.bookstore.dto

data class ShoppingCart(
    var shoppingCartItems: List<ShoppingCartItem> = emptyList(),
    var totalСost: Double = 0.0
)