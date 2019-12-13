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
    private val books: MutableLiveData<List<BookModel>> by lazy {
        MutableLiveData<List<BookModel>>().also {
            loadBooks()
        }
    }

    public fun getBooks(): LiveData<List<BookModel>> {
        return books
    }

    private fun loadBooks() {
        NetworkClient.getBookApiService().getBookList(null, null, "price,desc")
            .enqueue(object : Callback<BookCollectionApiResponse> {
                override fun onFailure(call: Call<BookCollectionApiResponse>, t: Throwable) {
                    Log.e("JB/error", "Load books failed", t)
                }

                override fun onResponse(
                    call: Call<BookCollectionApiResponse>,
                    response: Response<BookCollectionApiResponse>
                ) {
                    val body = response.body()!!
                    books.value = body._embedded.books
                    Log.i("JB/info", "Books have been loaded from server")
                }
            })
    }
}