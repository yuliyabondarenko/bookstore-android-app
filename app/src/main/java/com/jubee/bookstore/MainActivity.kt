package com.jubee.bookstore

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val BOOK_ID_EXTRA = "com.jubee.bookstore.BOOK_ID_EXTRA"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val adapter = BookAdapter { bookItem: BookDto -> bookItemClicked(bookItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MyLifecycleObserver())
        navigationView.setNavigationItemSelectedListener(this)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)
        bookRecyclerView.itemAnimator = DefaultItemAnimator()
        bookRecyclerView.adapter = adapter

        val booksViewModel = ViewModelProviders.of(this)[BooksViewModel::class.java]
        booksViewModel.booksLiveData.observe(this, Observer<List<BookDto>> { books ->
            adapter.data = books.toMutableList()
        })

        booksViewModel.isRefreshingLiveData.observe(this, Observer<Boolean> { isRefreshing ->
            swipeRefresh.isRefreshing = isRefreshing
        })

        swipeRefresh.setOnRefreshListener { booksViewModel.refresh() }
    }

    private fun bookItemClicked(bookItem: BookDto) {
        val intent = Intent(this, BookDetailsActivity::class.java).apply {
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