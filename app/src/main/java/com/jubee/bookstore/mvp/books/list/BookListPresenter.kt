package com.jubee.bookstore.mvp.books.list

import android.util.Log
import com.jubee.bookstore.mvp.AbstractPresenter
import com.jubee.bookstore.mvp.books.list.view.BookListView
import com.jubee.bookstore.service.NetworkService
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class BookListPresenter : AbstractPresenter<BookListView>() {

    override fun onFirstViewAttach() {
        loadBooks()
    }

    fun onRefreshBooks() {
        loadBooks()
    }

    private fun loadBooks() = launch {
        viewState.startLoadProgress()
        viewState.cleanError()
        try {
            val response = NetworkService.bookApi.getBookList()
            viewState.displayBooks(response._embedded.books)
        } catch (e: Exception) {
            val errorMsg = "Load books failed"
            viewState.showError(errorMsg)
            Log.e("JB/error", errorMsg, e)
        } finally {
            viewState.stopLoadProgress()
        }
    }

}