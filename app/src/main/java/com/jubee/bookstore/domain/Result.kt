package com.jubee.bookstore.domain

sealed class Result<D>
data class Success<D>(val data: D) : Result<D>()
data class Failure<D>(val errorMsg: String) : Result<D>()

