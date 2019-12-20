package com.jubee.bookstore.ui.activity.books.details.mvp

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil.setContentView
import com.jubee.bookstore.R
import com.jubee.bookstore.databinding.ActivityBookDetailsBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.mvp.books.details.BookDetailsPresenter
import com.jubee.bookstore.mvp.books.details.BookDetailsView
import com.jubee.bookstore.ui.activity.books.list.mvvm.BOOK_ID_EXTRA
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class BookDetailsMvpActivity : MvpAppCompatActivity(), BookDetailsView {
    @InjectPresenter
    lateinit var bookDetailsPresenter: BookDetailsPresenter

    @ProvidePresenter
    fun provideBookDetailsPresenter(): BookDetailsPresenter {
        return BookDetailsPresenter(intent.getLongExtra(BOOK_ID_EXTRA, 0))
    }

    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_book_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun displayBook(book: BookDto) {
        binding.book = book
    }

    override fun showError(errorMsg: String) {
        binding.error = BookstoreError(true, errorMsg)
    }

    override fun cleanError() {
        binding.error = BookstoreError(false)
    }

    override fun startLoadProgress() {
        binding.isLoading = true
    }

    override fun stopLoadProgress() {
        binding.isLoading = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
