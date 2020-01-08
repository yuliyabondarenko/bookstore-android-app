package com.jubee.bookstore.dto


data class BookDto(
    val id: Long = 0,
    val name: String?,
    val photo: String? = null,
    val absent: Boolean = false,
    val price: Double?
)