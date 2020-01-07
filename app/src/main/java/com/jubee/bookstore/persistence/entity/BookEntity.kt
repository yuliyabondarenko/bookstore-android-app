package com.jubee.bookstore.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "photo")
    val photo: String? = null,

    @ColumnInfo(name = "absent")
    val absent: Boolean = false,

    @ColumnInfo(name = "price")
    val price: Double?
)