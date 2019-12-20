package com.jubee.bookstore.etc

import android.util.Log
import java.lang.Exception
import java.lang.NullPointerException

sealed class Result<T>
class Success<T>(data: T) : Result<T>()
class Failure<T>(t: Throwable) : Result<T>()
class Loading<T> : Result<T>()

class StudyNotes {

    fun load() {
        try {
            //todo
        } catch (e: Exception) {
            if (e is NullPointerException) {
                Log.e("dfs", "asd", e)
            } else throw e
        }
    }

    fun load2() = castEx {
        //todo
    }

    fun <T> castEx(doto: () -> T) {
        try {
            doto()
        } catch (e: Exception) {
            if (e is NullPointerException) {
                Log.e("dfs", "asd", e)
            } else throw e
        }
    }
}