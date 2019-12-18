package com.jubee.bookstore.mvvm.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jubee.bookstore.api.BookCollectionApiResponse
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.service.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {
    private val _booksLiveData: MutableLiveData<List<BookDto>> by lazy {
        MutableLiveData<List<BookDto>>().also {
            loadBooks()
        }
    }

    val booksLiveData: LiveData<List<BookDto>>
        get() = _booksLiveData

    private val _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _error: MutableLiveData<BookstoreError> = MutableLiveData()

    val error: LiveData<BookstoreError>
        get() = _error

    private fun loadBooks() {
        _isLoadingLiveData.value = true
        _error.value = BookstoreError(false)
        NetworkClient.getBookApiService().getBookList(null, null, "price,desc")
            .enqueue(object : Callback<BookCollectionApiResponse> {
                override fun onFailure(call: Call<BookCollectionApiResponse>, t: Throwable) {
                    _isLoadingLiveData.value = false
                    val errorMsg = "Load books failed"
                    _error.value = BookstoreError(true, errorMsg)
                    Log.e("JB/error", errorMsg, t)
                }

                override fun onResponse(
                    call: Call<BookCollectionApiResponse>,
                    response: Response<BookCollectionApiResponse>
                ) {
                    val body = response.body()!!
                    _booksLiveData.value = body._embedded.books
                    _isLoadingLiveData.value = false
                }
            })
    }

    fun refresh() {
        loadBooks()
    }
}