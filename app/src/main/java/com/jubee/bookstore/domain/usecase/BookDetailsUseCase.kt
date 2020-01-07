package com.jubee.bookstore.domain.usecase

import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.dto.BookDto
import javax.inject.Inject

class BookDetailsUseCase @Inject constructor(private val bookApi: BookApi) : AbstractUseCase() {

    suspend fun getBookDetails(bookId: Long): Result<BookDto> {
        return withCtx("Can't obtain book details from server.") {
            bookApi.getBook(bookId)
        }
    }
}