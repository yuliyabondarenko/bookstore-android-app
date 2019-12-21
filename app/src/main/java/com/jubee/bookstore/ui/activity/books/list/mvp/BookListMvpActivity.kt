package com.jubee.bookstore.ui.activity.books.list.mvp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.R
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.mvp.books.list.BookListPresenter
import com.jubee.bookstore.mvp.books.list.view.BookListView
import com.jubee.bookstore.ui.activity.books.details.mvp.BookDetailsMvpActivity
import com.jubee.bookstore.ui.activity.books.list.mvvm.BOOK_ID_EXTRA
import com.jubee.bookstore.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_book_list_mvp.*
import kotlinx.android.synthetic.main.activity_book_list_mvvm.bookRecyclerView
import kotlinx.android.synthetic.main.activity_book_list_mvvm.swipeRefresh
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class BookListMvpActivity : MvpAppCompatActivity(), BookListView {

    @InjectPresenter
    lateinit var bookListPresenter: BookListPresenter

    private val adapter =
        BookAdapter { bookItem: BookDto ->
            bookItemClicked(bookItem)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list_mvp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)
        bookRecyclerView.itemAnimator = DefaultItemAnimator()
        bookRecyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener { bookListPresenter.onRefreshBooks() }
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

    private fun bookItemClicked(bookItem: BookDto) {
        val intent = Intent(this, BookDetailsMvpActivity::class.java).apply {
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
