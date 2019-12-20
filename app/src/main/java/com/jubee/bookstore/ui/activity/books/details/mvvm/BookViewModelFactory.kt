package com.jubee.bookstore.ui.activity.books.details.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubee.bookstore.mvvm.details.BookViewModel


class BookViewModelFactory(private val bookId: Long) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == BookViewModel::class.java) {
            BookViewModel(bookId) as T
        } else super.create(modelClass)
    }
}
