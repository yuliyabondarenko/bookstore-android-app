package com.jubee.bookstore.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("books")
    fun getBookList(
        @Query("size") size: Int?,
        @Query("page") page: Int?,
        @Query("sort") sort: String?
    ): Call<BookCollectionApiResponse>
}