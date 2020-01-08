package com.jubee.bookstore.presentation.mvp.books.list

import com.jubee.bookstore.domain.Failure
import com.jubee.bookstore.domain.Result
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.domain.usecase.BookListUseCase
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.mvp.AbstractPresenter
import com.jubee.bookstore.presentation.mvp.books.list.view.BookListView
import kotlinx.coroutines.flow.collect
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
        bookListUseCase.getBookList().collect {
            displayBookListResult(it)
        }
        viewState.stopLoadProgress()
    }

    private fun displayBookListResult(result: Result<List<BookDto>>) {
        when (result) {
            is Success -> viewState.displayBooks(result.data)
            is Failure -> viewState.showError("Load books failed. " + result.error.message)
        }
    }
}