package com.jubee.bookstore.mvp.books.details

import com.jubee.bookstore.dto.BookDto
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BookDetailsView : MvpView {

    fun displayBook(book: BookDto)

    fun showError(errorMsg: String)

    fun cleanError()

    fun startLoadProgress()

    fun stopLoadProgress()
}