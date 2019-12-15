package com.jubee.bookstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jubee.bookstore.databinding.ActivityBookDetailsBinding
import com.jubee.bookstore.viewmodel.BookViewModel

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_book_details)

        val bookId = intent.getLongExtra(BOOK_ID_EXTRA, 0)

        val bookViewModel = ViewModelProviders.of(this)[BookViewModel::class.java]
        bookViewModel.loadBook(bookId)
        bookViewModel.bookLiveData.observe(this, Observer { book ->
            binding.book = book
        })
    }
}
