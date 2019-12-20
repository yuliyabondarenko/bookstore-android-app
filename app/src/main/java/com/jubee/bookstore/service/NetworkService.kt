package com.jubee.bookstore.service

import com.jubee.bookstore.BuildConfig
import com.jubee.bookstore.api.BookApi
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val BASE_URL = "http://192.168.31.142:7070/api/"

    private val baseInterceptor: Interceptor = invoke { chain ->
        val request = chain
            .request()
            .newBuilder()
            .header(
                "Authorization", Credentials.basic("admin@mail.ru", "Qwe123")
            )
            .build()

        return@invoke chain.proceed(request)
    }

    private val loggingInterceptor = HttpLoggingInterceptor()
        .apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()


    val bookApi: BookApi by lazy {
        retrofit.create<BookApi>(BookApi::class.java)
    }
}