package com.jubee.bookstore.domain.usecase

import com.jubee.bookstore.convertor.toDto
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.persistence.dao.BookDao
import javax.inject.Inject

class BookDetailsUseCase @Inject constructor(private val bookDao: BookDao) : AbstractUseCase() {

    suspend fun getBookDetails(bookId: Long): Result<BookDto> {
        return withCtx("Can't obtain book details from local storage.") {
            bookDao.getBook(bookId).toDto()
        }
    }
}