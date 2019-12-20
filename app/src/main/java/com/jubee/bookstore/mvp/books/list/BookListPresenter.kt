package com.jubee.bookstore.mvp.books.list

import android.util.Log
import com.jubee.bookstore.api.BookCollectionApiResponse
import com.jubee.bookstore.mvp.books.list.view.BookListView
import com.jubee.bookstore.service.NetworkService
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class BookListPresenter : MvpPresenter<BookListView>() {

    override fun onFirstViewAttach() {
        loadBooks()
    }

    fun onRefreshBooks() {
        loadBooks()
    }

    private fun loadBooks() {
        viewState.startLoadProgress()
        viewState.cleanError()
        NetworkService.bookApi.getBookList(null, null, "price,desc")
            .enqueue(object : Callback<BookCollectionApiResponse> {
                override fun onFailure(call: Call<BookCollectionApiResponse>, t: Throwable) {
                    viewState.stopLoadProgress()
                    val errorMsg = "Load books failed"
                    viewState.showError(errorMsg)
                    Log.e("JB/error", errorMsg, t)
                }

                override fun onResponse(
                    call: Call<BookCollectionApiResponse>,
                    response: Response<BookCollectionApiResponse>
                ) {
                    val body = response.body()!!
                    viewState.displayBooks(body._embedded.books)
                    viewState.stopLoadProgress()
                }
            })
    }

}