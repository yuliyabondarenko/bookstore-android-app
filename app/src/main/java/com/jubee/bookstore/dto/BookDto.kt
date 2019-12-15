package com.jubee.bookstore.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BookDto(
    val id: Long = 0,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("photo")
    @Expose
    var photo: String? = null,

    @SerializedName("absent")
    @Expose
    var absent: Boolean = false,

    @SerializedName("price")
    @Expose
    var price: Double
)