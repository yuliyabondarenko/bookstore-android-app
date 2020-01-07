package com.jubee.bookstore.convertor

import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.persistence.entity.BookEntity
import org.mapstruct.Mapper

@Mapper
interface BookConverter {
    fun toDto(book: BookEntity): BookDto

    fun toEntity(book: BookDto): BookEntity

    fun toDTO(books: Collection<BookEntity>): List<BookDto>

    fun toEntity(books: Collection<BookDto>): List<BookEntity>
}