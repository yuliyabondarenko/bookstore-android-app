package com.jubee.bookstore.etc

data class BookstoreError(val isPresent: Boolean, val errorMsg: String = "") {
}