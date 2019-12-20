package com.jubee.bookstore.mvp.books.details

import android.util.Log
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.mvp.books.details.view.BookDetailsView
import com.jubee.bookstore.service.NetworkClient
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class BookDetailsPresenter(private val bookId: Long) : MvpPresenter<BookDetailsView>() {

    override fun onFirstViewAttach() {
        loadBook()
    }

    private fun loadBook() {
        viewState.startLoadProgress()
        viewState.cleanError()
        NetworkClient.getBookApiService().getBook(bookId)
            .enqueue(object : Callback<BookDto> {
                override fun onFailure(call: Call<BookDto>, t: Throwable) {
                    viewState.stopLoadProgress()
                    val errorMsg = "Load book failed"
                    viewState.showError(errorMsg)
                    Log.e("JB/error", errorMsg, t)
                }

                override fun onResponse(
                    call: Call<BookDto>,
                    response: Response<BookDto>
                ) {
                    viewState.displayBook(response.body()!!)
                    viewState.stopLoadProgress()
                }
            })
    }
}
