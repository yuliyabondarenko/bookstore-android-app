package com.jubee.bookstore.di.module

import android.app.Application
import androidx.room.Room
import com.jubee.bookstore.persistence.BookstoreDatabase
import com.jubee.bookstore.persistence.dao.BookDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton


@Module
class PersistenceModule(application: Application) {

    private var database: BookstoreDatabase

    init {
        database = Room.databaseBuilder(
            application, BookstoreDatabase::class.java, "db-bookstore"
        ).build()
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): BookstoreDatabase {
        return database
    }

    @Singleton
    @Provides
    fun providesBookDao(database: BookstoreDatabase): BookDao {
        return database.bookDao()
    }

}