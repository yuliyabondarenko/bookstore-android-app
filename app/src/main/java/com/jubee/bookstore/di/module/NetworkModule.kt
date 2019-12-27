package com.jubee.bookstore.di.module

import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.network.NetworkService
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideBookApi(): BookApi {
        return NetworkService.bookApi
    }
}