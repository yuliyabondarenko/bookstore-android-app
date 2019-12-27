package com.jubee.bookstore.etc

import android.util.Log
import java.lang.Exception
import java.lang.NullPointerException

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