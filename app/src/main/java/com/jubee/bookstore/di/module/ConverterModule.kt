package com.jubee.bookstore.di.module

import com.jubee.bookstore.convertor.BookConverter
import dagger.Module
import dagger.Provides
import org.mapstruct.factory.Mappers

@Module
class ConverterModule {

    @Provides
    fun provideBookConverter(): BookConverter {
        return Mappers.getMapper(BookConverter::class.java)
    }
}