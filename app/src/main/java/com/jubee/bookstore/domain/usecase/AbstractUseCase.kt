package com.jubee.bookstore.domain.usecase

import android.util.Log
import com.jubee.bookstore.domain.Failure
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.domain.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class AbstractUseCase {

    suspend fun <T> withCtx(
        additionalErrorInfo: String = "Unknown scope",
        todo: suspend () -> T
    ): Result<T> = withContext(Dispatchers.Default) {
        try {
            Success(todo())
        } catch (e: Exception) {
            Log.e("JB/error", additionalErrorInfo, e)
            Failure<T>(Exception(additionalErrorInfo, e))
        }
    }
}