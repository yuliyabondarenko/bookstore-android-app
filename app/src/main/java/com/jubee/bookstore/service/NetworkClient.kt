package com.jubee.bookstore.service

import com.jubee.bookstore.BuildConfig
import com.jubee.bookstore.api.BookApiService
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO How to rename?
object NetworkClient {

    private const val BASE_URL = "http://192.168.31.142:7070/api/"

    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        })
        .addInterceptor { chain ->
            val originalRequest = chain.request()

            val builder = originalRequest.newBuilder().header(
                "Authorization",
                Credentials.basic("admin@mail.ru", "Qwe123")
            )

            val newRequest = builder.build()
            chain.proceed(newRequest)
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    public fun getBookApiService(): BookApiService {
        return retrofit.create<BookApiService>(BookApiService::class.java)
    }
}