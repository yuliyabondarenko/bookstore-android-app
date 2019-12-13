package com.jubee.bookstore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.model.BookModel
import com.jubee.bookstore.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val BOOK_ID_EXTRA = "com.jubee.bookstore.BOOK_ID_EXTRA"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MyLifecycleObserver())

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)
        bookRecyclerView.itemAnimator = DefaultItemAnimator()

        val booksViewModel = ViewModelProviders.of(this)[BooksViewModel::class.java]
        booksViewModel.getBooks().observe(this, Observer<List<BookModel>> { books ->
            bookRecyclerView.adapter =
                BookAdapter(books) { bookItem: BookModel -> bookItemClicked(bookItem) }
        })
    }

    private fun bookItemClicked(bookItem: BookModel) {
        val intent = Intent(this, BookDetailsActivity::class.java).apply {
            putExtra(BOOK_ID_EXTRA, bookItem.id)
        }
        startActivity(intent)
    }

}