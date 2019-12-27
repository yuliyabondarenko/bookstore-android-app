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

class BookListUseCase @Inject constructor(private val bookApi: BookApi) {

    suspend fun getBookList(): Result<List<BookDto>> {
        return GlobalScope.async {
            try {
                Success(bookApi.getBookList()._embedded.books)
            } catch (e: Exception) {
                val errorMsg = "Can't obtain books from server."
                Log.e("JB/error", errorMsg, e)
                Failure<List<BookDto>>(errorMsg)
            }
        }.await()
    }
}