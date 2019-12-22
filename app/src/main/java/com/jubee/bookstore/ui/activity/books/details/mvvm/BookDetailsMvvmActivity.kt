package com.jubee.bookstore.ui.activity.books.details.mvvm

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jubee.bookstore.R
import com.jubee.bookstore.databinding.ActivityBookDetailsBinding
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.mvvm.details.BookDetailsViewModel
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA


class BookDetailsMvvmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_book_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bookId = intent.getLongExtra(BOOK_ID_EXTRA, 0)

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
