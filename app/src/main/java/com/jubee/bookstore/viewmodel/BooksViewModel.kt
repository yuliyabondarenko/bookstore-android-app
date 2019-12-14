package com.jubee.bookstore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jubee.bookstore.api.BookCollectionApiResponse
import com.jubee.bookstore.model.BookModel
import com.jubee.bookstore.service.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {
    val booksLiveData: LiveData<List<BookModel>>
        get() = _booksLiveData

    val isRefreshingLiveData: LiveData<Boolean>
        get() = _isRefreshingLiveData

    private val _booksLiveData: MutableLiveData<List<BookModel>> by lazy {
        MutableLiveData<List<BookModel>>().also {
            loadBooks()
        }
    }

    private val _isRefreshingLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private fun loadBooks() {
        _isRefreshingLiveData.value = true
        NetworkClient.getBookApiService().getBookList(null, null, "price,desc")
            .enqueue(object : Callback<BookCollectionApiResponse> {
                override fun onFailure(call: Call<BookCollectionApiResponse>, t: Throwable) {
                    _isRefreshingLiveData.value = false
                    Log.e("JB/error", "Load books failed", t)
                }

                override fun onResponse(
                    call: Call<BookCollectionApiResponse>,
                    response: Response<BookCollectionApiResponse>
                ) {
                    val body = response.body()!!
                    _booksLiveData.value = body._embedded.books
                    _isRefreshingLiveData.value = false
                    Log.i("JB/info", "Books have been loaded from server")
                }
            })
    }

    fun refresh() {
        loadBooks()
    }
}