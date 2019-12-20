package com.jubee.bookstore.ui.activity.books.details.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubee.bookstore.mvvm.details.BookDetailsViewModel


class BookDetailsViewModelFactory(private val bookId: Long) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == BookDetailsViewModel::class.java) {
            BookDetailsViewModel(bookId) as T
        } else super.create(modelClass)
    }
}
