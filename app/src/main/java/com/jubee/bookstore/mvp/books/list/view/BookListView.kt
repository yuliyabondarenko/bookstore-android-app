package com.jubee.bookstore.mvp.books.list.view

import com.jubee.bookstore.dto.BookDto
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BookListView : MvpView {

    fun displayBooks(books: List<BookDto>)

    fun showError(errorMsg: String)

    fun cleanError()

    fun startLoadProgress()

    fun stopLoadProgress()
}