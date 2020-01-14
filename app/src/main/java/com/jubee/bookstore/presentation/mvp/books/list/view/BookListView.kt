package com.jubee.bookstore.presentation.mvp.books.list.view

import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.mvp.AbstractMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BookListView : AbstractMvpView {

    fun displayBooks(books: List<BookDto>)
}