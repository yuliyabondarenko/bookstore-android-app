package com.jubee.bookstore.mvp.books.details

import android.util.Log
import com.jubee.bookstore.mvp.AbstractPresenter
import com.jubee.bookstore.mvp.books.details.view.BookDetailsView
import com.jubee.bookstore.service.NetworkService
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BookDetailsPresenter @Inject constructor() : AbstractPresenter<BookDetailsView>() {
    private var bookId: Long = 0

    fun init(bookId: Long) {
        this.bookId = bookId
    }

    override fun onFirstViewAttach() {
        loadBook()
    }

    private fun loadBook() = launch {
        viewState.startLoadProgress()
        viewState.cleanError()
        try {
            val response = NetworkService.bookApi.getBook(bookId)
            viewState.displayBook(response)
        } catch (e: Exception) {
            val errorMsg = "Load book failed"
            viewState.showError(errorMsg)
            Log.e("JB/error", errorMsg, e)
        } finally {
            viewState.stopLoadProgress()
        }
    }
}
