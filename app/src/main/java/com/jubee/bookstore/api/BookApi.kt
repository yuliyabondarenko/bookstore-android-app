package com.jubee.bookstore.api

import com.jubee.bookstore.dto.BookDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {
    @GET("books")
    suspend fun getBookList(
        @Query("sort") sort: String? = "price,desc",
        @Query("size") size: Int? = null,
        @Query("page") page: Int? = null
    ): BookCollectionApiResponse

    @GET("books/{id}")
    suspend fun getBook(@Path("id") id: Long): BookDto
}