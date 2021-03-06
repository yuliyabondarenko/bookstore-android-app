package com.jubee.bookstore.ui.fragment.books.details.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jubee.bookstore.R
import com.jubee.bookstore.app.BookstoreApplication
import com.jubee.bookstore.databinding.FragmentBookDetailsBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.ErrorPresence
import com.jubee.bookstore.presentation.mvp.books.details.BookDetailsPresenter
import com.jubee.bookstore.presentation.mvp.books.details.view.BookDetailsView
import com.jubee.bookstore.ui.fragment.AbstractFragment
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA
import moxy.ktx.moxyPresenter


class BookDetailsMvpFragment : AbstractFragment<BookDetailsPresenter>(), BookDetailsView {

    private val presenter by moxyPresenter {
        val bookId = this.arguments!!.getLong(BOOK_ID_EXTRA)
        presenterProvider.get().apply { init(bookId) }
    }

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_book_details, container, false)
        return binding.root
    }

    override fun displayBook(book: BookDto) {
        binding.book = book
    }

    override fun showError(errorMsg: String?) {
        binding.error = ErrorPresence(true, errorMsg)
    }

    override fun cleanError() {
        binding.error = ErrorPresence(false)
    }

    override fun startLoadProgress() {
        binding.isLoading = true
    }

    override fun stopLoadProgress() {
        binding.isLoading = false
    }
}
