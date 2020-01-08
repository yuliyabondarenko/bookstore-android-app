package com.jubee.bookstore.domain.usecase

import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.convertor.toDto
import com.jubee.bookstore.convertor.toEntity
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.persistence.dao.BookDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookListUseCase @Inject constructor(
    private val bookApi: BookApi,
    private val bookDao: BookDao
) : AbstractUseCase() {

    fun getBookList(): Flow<Result<List<BookDto>>> = flow {
        val booksLocal = getLocal()
        emit(booksLocal)
        val booksRemote = getRemote()
        if (booksRemote is Success) {
            updateLocal(booksRemote.data)
        }
        emit(booksRemote)
    }

    private suspend fun updateLocal(booksRemote: List<BookDto>) {
        val entities = booksRemote.map { it.toEntity() }
        withCtx("Can't update books in local storage") {
            bookDao.insert(entities)
        }
    }

    private suspend fun getLocal(): Result<List<BookDto>> =
        withCtx("Can't obtain books from local storage.") {
            bookDao.getAll().map { it.toDto() }
        }

    private suspend fun getRemote(): Result<List<BookDto>> =
        withCtx("Can't obtain books from server.") {
            bookApi.getBookList()._embedded.books
        }
}