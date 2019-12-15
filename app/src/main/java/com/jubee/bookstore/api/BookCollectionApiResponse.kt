package com.jubee.bookstore.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jubee.bookstore.dto.BookDto

class BookCollectionApiResponse(@SerializedName("_embedded") @Expose val _embedded: Embedded) {

    class Embedded(var books: List<BookDto>)
}