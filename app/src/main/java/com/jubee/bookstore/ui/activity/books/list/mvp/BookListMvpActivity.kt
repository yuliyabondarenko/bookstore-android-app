package com.jubee.bookstore.ui.activity.books.list.mvp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.R
import com.jubee.bookstore.databinding.ActivityBookListBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.mvp.books.list.BookListPresenter
import com.jubee.bookstore.mvp.books.list.view.BookListView
import com.jubee.bookstore.ui.activity.books.details.mvp.BookDetailsMvpActivity
import com.jubee.bookstore.ui.activity.books.list.mvvm.BOOK_ID_EXTRA
import com.jubee.bookstore.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_book_list.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class BookListMvpActivity : MvpAppCompatActivity(), BookListView {

    @InjectPresenter
    lateinit var bookListPresenter: BookListPresenter

    private lateinit var binding: ActivityBookListBinding

    private val adapter =
        BookAdapter { bookItem: BookDto ->
            bookItemClicked(bookItem)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_book_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 3)
        bookRecyclerView.itemAnimator = DefaultItemAnimator()
        bookRecyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener { bookListPresenter.onRefreshBooks() }
    }

    override fun startLoadProgress() {
        binding.isLoading = true
    }

    override fun stopLoadProgress() {
        binding.isLoading = false
    }

    override fun displayBooks(books: List<BookDto>) {
        adapter.data = books.toMutableList()
    }

    override fun showError(errorMsg: String) {
        binding.error = BookstoreError(true, errorMsg)
    }

    override fun cleanError() {
        binding.error = BookstoreError(false)
    }

    private fun bookItemClicked(bookItem: BookDto) {
        val intent = Intent(this, BookDetailsMvpActivity::class.java)
            .apply {
                putExtra(BOOK_ID_EXTRA, bookItem.id)
            }
        startActivity(intent)
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
