package com.jubee.bookstore.presentation.mvp.books.details

import com.jubee.bookstore.domain.Failure
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.domain.usecase.BookDetailsUseCase
import com.jubee.bookstore.presentation.mvp.AbstractPresenter
import com.jubee.bookstore.presentation.mvp.books.details.view.BookDetailsView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BookDetailsPresenter @Inject constructor(private val bookDetailsUseCase: BookDetailsUseCase) :
    AbstractPresenter<BookDetailsView>() {
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
        when (val result = bookDetailsUseCase.getBookDetails(bookId)) {
            is Success -> viewState.displayBook(result.data)
            is Failure -> viewState.showError(result.error.message)
        }
        viewState.stopLoadProgress()
    }
}
