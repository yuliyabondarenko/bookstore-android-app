package com.jubee.bookstore.mvp.books.list

import com.jubee.bookstore.domain.Failure
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.domain.usecase.BookListUseCase
import com.jubee.bookstore.mvp.AbstractPresenter
import com.jubee.bookstore.mvp.books.list.view.BookListView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BookListPresenter @Inject constructor(
    private val bookListUseCase: BookListUseCase
) : AbstractPresenter<BookListView>() {

    override fun onFirstViewAttach() {
        loadBooks()
    }

    fun onRefreshBooks() {
        loadBooks()
    }

    private fun loadBooks() = launch {
        viewState.startLoadProgress()
        viewState.cleanError()
        when (val result = bookListUseCase.getBookList()) {
            is Success -> viewState.displayBooks(result.data)
            is Failure -> viewState.showError("Load books failed. " + result.errorMsg)
        }
        viewState.stopLoadProgress()
    }

}