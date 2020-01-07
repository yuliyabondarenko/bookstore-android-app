package com.jubee.bookstore.persistence.dao

import androidx.room.*
import com.jubee.bookstore.persistence.entity.BookEntity

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAll(): List<BookEntity>


    @Query("SELECT * FROM book WHERE id=:id ")
    fun getBookById(id: Long): BookEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(books: List<BookEntity>)
}