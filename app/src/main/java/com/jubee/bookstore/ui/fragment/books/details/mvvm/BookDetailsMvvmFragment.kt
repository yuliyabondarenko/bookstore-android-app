package com.jubee.bookstore.ui.fragment.books.details.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jubee.bookstore.R
import com.jubee.bookstore.app.BookstoreApplication
import com.jubee.bookstore.databinding.FragmentBookDetailsBinding
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.mvvm.details.BookDetailsViewModel
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class BookDetailsMvvmFragment : MvpAppCompatFragment() {
    @Inject
    lateinit var viewModelFactory: BookDetailsViewModel.Factory

    private lateinit var binding: FragmentBookDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        BookstoreApplication.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_book_details, container, false)

        val bookId = this.arguments!!.getLong(BOOK_ID_EXTRA)

        val bookViewModel = ViewModelProviders.of(
            this, viewModelFactory
        )[BookDetailsViewModel::class.java]
            .apply { init(bookId) }

        setUpObservers(bookViewModel)

        return binding.root
    }

    private fun setUpObservers(bookViewModel: BookDetailsViewModel) {
        bookViewModel.bookLiveData.observe(this, Observer { book ->
            binding.book = book
        })

        bookViewModel.isLoadingLiveData.observe(this, Observer<Boolean> { isLoading ->
            binding.isLoading = isLoading
        })

        bookViewModel.errorLiveData.observe(this, Observer<BookstoreError> { error ->
            binding.error = error
        })
    }
}
