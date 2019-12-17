package com.jubee.bookstore.mvp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.BOOK_ID_EXTRA
import com.jubee.bookstore.BookAdapter
import com.jubee.bookstore.BookDetailsActivity
import com.jubee.bookstore.R
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.mvp.books.list.BooksPresenter
import com.jubee.bookstore.mvp.books.list.view.BooksView
import kotlinx.android.synthetic.main.activity_books_mvp.*
import kotlinx.android.synthetic.main.activity_main.bookRecyclerView
import kotlinx.android.synthetic.main.activity_main.swipeRefresh
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class BooksMvpActivity : MvpAppCompatActivity(), BooksView {

    @InjectPresenter
    lateinit var booksPresenter: BooksPresenter

    private val adapter = BookAdapter { bookItem: BookDto ->
        bookItemClicked(bookItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_mvp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)
        bookRecyclerView.itemAnimator = DefaultItemAnimator()
        bookRecyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener { booksPresenter.onRefreshBooks() }
    }

    override fun startLoadProgress() {
        swipeRefresh.isRefreshing = true
    }

    override fun stopLoadProgress() {
        swipeRefresh.isRefreshing = false
    }

    override fun displayBooks(books: List<BookDto>) {
        adapter.data = books.toMutableList()
    }

    override fun showError(errorMsg: String) {
        errorMsgView.text = errorMsg
        errorMsgView.visibility = View.VISIBLE
    }

    override fun cleanError() {
        errorMsgView.text = null
        errorMsgView.visibility = View.GONE
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

    private fun bookItemClicked(bookItem: BookDto) {
        val intent = Intent(this, BookDetailsActivity::class.java).apply {
            putExtra(BOOK_ID_EXTRA, bookItem.id)
        }
        startActivity(intent)
    }
}
