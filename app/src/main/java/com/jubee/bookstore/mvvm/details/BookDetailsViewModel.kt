package com.jubee.bookstore.mvvm.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.service.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetailsViewModel(bookId: Long) : ViewModel() {

    private val _bookLiveData: MutableLiveData<BookDto> by lazy {
        MutableLiveData<BookDto>().also { loadBook(bookId) }
    }

    val bookLiveData: LiveData<BookDto>
        get() = _bookLiveData

    private val _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _error: MutableLiveData<BookstoreError> = MutableLiveData()

    val error: LiveData<BookstoreError>
        get() = _error

    private fun loadBook(bookId: Long) {
        _isLoadingLiveData.value = true
        _error.value = BookstoreError(false)
        NetworkClient.getBookApiService().getBook(bookId)
            .enqueue(object : Callback<BookDto> {
                override fun onFailure(call: Call<BookDto>, t: Throwable) {
                    _isLoadingLiveData.value = false
                    val errorMsg = "Load book failed"
                    _error.value = BookstoreError(true, errorMsg)
                    Log.e("JB/error", errorMsg, t)
                }

                override fun onResponse(
                    call: Call<BookDto>,
                    response: Response<BookDto>
                ) {
                    _bookLiveData.value = response.body()
                    _isLoadingLiveData.value = false
                }

            })
    }
}
