package com.jubee.bookstore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.service.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {

    val bookLiveData: LiveData<BookDto>
        get() = _bookLiveData

    private val _bookLiveData: MutableLiveData<BookDto> by lazy {
        MutableLiveData<BookDto>()
    }

    fun loadBook(bookId: Long) {
        NetworkClient.getBookApiService().getBook(bookId)
            .enqueue(object : Callback<BookDto> {
                override fun onResponse(
                    call: Call<BookDto>,
                    response: Response<BookDto>
                ) {
                    _bookLiveData.value = response.body()
                }

                override fun onFailure(call: Call<BookDto>, t: Throwable) {
                    Log.e("JB/error", "Load book failed", t)
                    //TODO add liveData for error?
                }

            })
    }
}
