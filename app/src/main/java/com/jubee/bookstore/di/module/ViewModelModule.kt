package com.jubee.bookstore.di.module

import com.jubee.bookstore.domain.usecase.BookDetailsUseCase
import com.jubee.bookstore.presentation.mvvm.books.details.BookDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideBookDetailsViewModelFactory(useCase: BookDetailsUseCase) = BookDetailsViewModel.Factory(useCase)
}