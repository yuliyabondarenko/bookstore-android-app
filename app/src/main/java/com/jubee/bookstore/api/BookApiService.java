package com.jubee.bookstore.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookApiService {
    @GET("books")
    Call<BookCollectionApiResponse> getBookList();
}