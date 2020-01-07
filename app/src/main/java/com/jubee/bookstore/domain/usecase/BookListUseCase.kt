package com.jubee.bookstore.domain.usecase

import android.util.Log
import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.convertor.toDto
import com.jubee.bookstore.convertor.toEntity
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.persistence.dao.BookDao
import javax.inject.Inject

class BookListUseCase @Inject constructor(
    private val bookApi: BookApi,
    private val bookDao: BookDao

) : AbstractUseCase() {
    suspend fun getBookList(): Result<List<BookDto>> {
        val booksLocal = getLocal()
        Log.i("JB/local-books", booksLocal.toString())
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

    private suspend fun updateLocal(booksRemote: List<BookDto>) {
        val entities = booksRemote.map { it.toEntity() }
        withCtx("Can't update books in local storage") {
            bookDao.insert(entities)
        }
    }

    private suspend fun getLocal(): Result<List<BookDto>> =
        withCtx("Can't obtain books from local storage.") {
            val books = bookDao.getAll()
            books.map { it.toDto() }
        }

    private suspend fun getRemote(): Result<List<BookDto>> = withCtx(
        "Can't obtain books from server."
    ) {
        bookApi.getBookList()._embedded.books
    }
}