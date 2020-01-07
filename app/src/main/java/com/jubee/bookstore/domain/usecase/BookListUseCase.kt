package com.jubee.bookstore.domain.usecase

import android.util.Log
import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.convertor.BookConverter
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.persistence.dao.BookDao
import javax.inject.Inject

class BookListUseCase @Inject constructor(
    private val bookApi: BookApi,
    private val bookDao: BookDao,
    private val bookConverter: BookConverter

) : AbstractUseCase() {
    suspend fun getBookList(): Result<List<BookDto>> {
        val booksLocal = getLocal()
        //display local on UI
        // I need flow to emit Flowable? (on continue)
        // emit Local
        val booksRemote = getRemote()
        if (booksRemote is Success) {
            updateLocal(booksRemote.data)
        }
        // emit Remote
        return booksRemote
    }

    private fun updateLocal(booksRemote: List<BookDto>) {
        val entities = bookConverter.toEntity(booksRemote)
        Log.i("JB/converted-books", entities.toString())
        //bookDao.update(entities)
    }

    private suspend fun getLocal(): Result<List<BookDto>> =
        withCtx("Can't obtain books from local storage.") {
            bookConverter.toDTO(bookDao.getAll())
        }

    private suspend fun getRemote(): Result<List<BookDto>> = withCtx(
        "Can't obtain books from server."
    ) {
        bookApi.getBookList()._embedded.books
    }
}