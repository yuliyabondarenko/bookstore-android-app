package com.jubee.bookstore.presentation.mvp.books.details.view

import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.mvp.AbstractMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BookDetailsView : AbstractMvpView {

    fun displayBook(book: BookDto)
}