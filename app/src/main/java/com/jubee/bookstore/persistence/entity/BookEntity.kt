package com.jubee.bookstore.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey
    val id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "photo")
    var photo: String? = null,

    @ColumnInfo(name = "absent")
    var absent: Boolean = false,

    @ColumnInfo(name = "price")
    var price: Double?
) {
    constructor() : this(0, null, null, false, null)
}