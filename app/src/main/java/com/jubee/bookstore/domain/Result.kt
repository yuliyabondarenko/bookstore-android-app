package com.jubee.bookstore.domain

import java.lang.Exception

sealed class Result<D>
data class Success<D>(val data: D) : Result<D>()
data class Failure<D>(val error: Exception) : Result<D>()

