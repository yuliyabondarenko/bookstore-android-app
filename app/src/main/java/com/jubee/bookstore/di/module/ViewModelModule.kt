package com.jubee.bookstore.di.module

import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.mvvm.details.BookDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideBookDetailsViewModelFactory(bookApi: BookApi) = BookDetailsViewModel.Factory(bookApi)
}