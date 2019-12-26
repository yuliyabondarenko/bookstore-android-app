package com.jubee.bookstore.domain.usecase

import android.util.Log
import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.domain.Failure
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.dto.BookDto
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class BookDetailsUseCase @Inject constructor(private val bookApi: BookApi) {

    suspend fun getBookDetails(bookId: Long): Result<BookDto> {
        return GlobalScope.async {
            try {
                Success(bookApi.getBook(bookId))
            } catch (e: Exception) {
                val errorMsg = "Can't obtain book details from server."
                Log.e("JB/error", errorMsg, e)
                Failure<BookDto>(errorMsg)
            }
        }.await()
    }
}