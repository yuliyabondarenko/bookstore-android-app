package com.jubee.bookstore.convertor

import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.persistence.entity.BookEntity


fun BookDto.toEntity() = BookEntity(id, name, photo, absent, price)

fun BookEntity.toDto() = BookDto(id, name, photo, absent, price)