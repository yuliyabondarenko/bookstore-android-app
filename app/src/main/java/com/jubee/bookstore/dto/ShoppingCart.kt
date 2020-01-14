package com.jubee.bookstore.dto

data class ShoppingCart(
    val shoppingCartItems: List<ShoppingCartItem> = emptyList(),
    val totalСost: Double = 0.0
)