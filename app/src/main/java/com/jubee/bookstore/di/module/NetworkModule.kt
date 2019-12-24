package com.jubee.bookstore.di.module

import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.mvp.books.details.BookDetailsPresenter
import com.jubee.bookstore.service.NetworkService
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideBookApi(): BookApi {
        return NetworkService.bookApi
    }
}