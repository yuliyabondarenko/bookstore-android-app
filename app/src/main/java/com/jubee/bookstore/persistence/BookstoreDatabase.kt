package com.jubee.bookstore.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jubee.bookstore.persistence.dao.BookDao
import com.jubee.bookstore.persistence.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookstoreDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

}