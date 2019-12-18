package com.jubee.bookstore.ui.activity.books.list

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.jubee.bookstore.R
import com.jubee.bookstore.ui.adapter.BookAdapter
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.MyLifecycleObserver
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.ui.activity.books.details.BookDetailsMvvmActivity
import com.jubee.bookstore.mvvm.list.BooksViewModel
import kotlinx.android.synthetic.main.activity_books_mvvm.*

const val BOOK_ID_EXTRA = "com.jubee.bookstore.ui.activity.books.list.BOOK_ID_EXTRA"

class BooksMvvmActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val adapter =
        BookAdapter { bookItem: BookDto ->
            bookItemClicked(bookItem)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_mvvm)

        lifecycle.addObserver(MyLifecycleObserver())
        navigationView.setNavigationItemSelectedListener(this)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)
        bookRecyclerView.itemAnimator = DefaultItemAnimator()
        bookRecyclerView.adapter = adapter

        val booksViewModel = ViewModelProviders.of(this)[BooksViewModel::class.java]

        booksViewModel.booksLiveData.observe(this, Observer<List<BookDto>> { books ->
            adapter.data = books.toMutableList()
        })

        booksViewModel.isLoadingLiveData.observe(this, Observer<Boolean> { isLoading ->
            swipeRefresh.isRefreshing = isLoading
        })

        booksViewModel.error.observe(this, Observer<BookstoreError> { error ->
            errorMsgView.text = error.errorMsg
            if (error.isPresent) errorMsgView.visibility = View.VISIBLE
            else errorMsgView.visibility = View.GONE
        })

        swipeRefresh.setOnRefreshListener { booksViewModel.refresh() }
    }

    private fun bookItemClicked(bookItem: BookDto) {
        val intent = Intent(this, BookDetailsMvvmActivity::class.java).apply {
            putExtra(BOOK_ID_EXTRA, bookItem.id)
        }
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.booksMvp -> startActivity(Intent(this, BooksMvpActivity::class.java))
        }
        return true
    }

}