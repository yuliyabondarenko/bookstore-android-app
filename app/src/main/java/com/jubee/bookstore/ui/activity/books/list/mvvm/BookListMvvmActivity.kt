package com.jubee.bookstore.ui.activity.books.list.mvvm

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.jubee.bookstore.R
import com.jubee.bookstore.databinding.ActivityBookListMvvmBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.etc.MyLifecycleObserver
import com.jubee.bookstore.mvvm.list.BookListViewModel
import com.jubee.bookstore.ui.activity.books.details.mvvm.BookDetailsMvvmActivity
import com.jubee.bookstore.ui.activity.books.list.mvp.BookListMvpActivity
import com.jubee.bookstore.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_book_list_mvvm.*

const val BOOK_ID_EXTRA = "com.jubee.bookstore.ui.activity.books.list.mvvm.BOOK_ID_EXTRA"

class BookListMvvmActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityBookListMvvmBinding

    private val adapter =
        BookAdapter { bookItem: BookDto ->
            bookItemClicked(bookItem)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_book_list_mvvm)

        lifecycle.addObserver(MyLifecycleObserver())
        navigationView.setNavigationItemSelectedListener(this)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)
        bookRecyclerView.itemAnimator = DefaultItemAnimator()
        bookRecyclerView.adapter = adapter

        val bookListViewModel = ViewModelProviders.of(this)[BookListViewModel::class.java]

        bookListViewModel.booksLiveData.observe(this, Observer<List<BookDto>> { books ->
            adapter.data = books.toMutableList()
        })

        bookListViewModel.isLoadingLiveData.observe(this, Observer<Boolean> { isLoading ->
            binding.isLoading = isLoading
        })

        bookListViewModel.error.observe(this, Observer<BookstoreError> { error ->
            binding.error = error
        })

        swipeRefresh.setOnRefreshListener { bookListViewModel.refresh() }
    }

    private fun bookItemClicked(bookItem: BookDto) {
        val intent = Intent(this, BookDetailsMvvmActivity::class.java).apply {
            putExtra(BOOK_ID_EXTRA, bookItem.id)
        }
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookListMvp -> startActivity(Intent(this, BookListMvpActivity::class.java))
        }
        return true
    }

}