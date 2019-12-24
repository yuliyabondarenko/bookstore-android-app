package com.jubee.bookstore.ui.fragment.books.details.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jubee.bookstore.R
import com.jubee.bookstore.databinding.FragmentBookDetailsBinding
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.mvvm.details.BookDetailsViewModel
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA
import moxy.MvpAppCompatFragment


class BookDetailsMvvmFragment : MvpAppCompatFragment() {
    private lateinit var binding: FragmentBookDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_details, container, false)

        val bookId = this.arguments!!.getLong(BOOK_ID_EXTRA)

        val bookViewModel = ViewModelProviders.of(
            this, BookDetailsViewModelFactory(bookId)
        )[BookDetailsViewModel::class.java]

        bookViewModel.bookLiveData.observe(this, Observer { book ->
            binding.book = book
        })

        bookViewModel.isLoadingLiveData.observe(this, Observer<Boolean> { isLoading ->
            binding.isLoading = isLoading
        })

        bookViewModel.errorLiveData.observe(this, Observer<BookstoreError> { error ->
            binding.error = error
        })

        return binding.root
    }
}
