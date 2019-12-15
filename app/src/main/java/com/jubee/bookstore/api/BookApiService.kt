package com.jubee.bookstore.api

import com.jubee.bookstore.dto.BookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("books")
    fun getBookList(
        @Query("size") size: Int?,
        @Query("page") page: Int?,
        @Query("sort") sort: String?
    ): Call<BookCollectionApiResponse>

    @GET("books/{id}")
    fun getBook(@Path("id") id: Long): Call<BookDto>
}