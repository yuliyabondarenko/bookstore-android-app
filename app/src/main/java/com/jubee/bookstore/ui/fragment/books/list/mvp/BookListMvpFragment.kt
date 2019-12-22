package com.jubee.bookstore.ui.fragment.books.list.mvp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.R
import com.jubee.bookstore.databinding.FragmentBookListBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.mvp.books.list.BookListPresenter
import com.jubee.bookstore.mvp.books.list.view.BookListView
import com.jubee.bookstore.ui.activity.books.details.mvp.BookDetailsMvpActivity
import com.jubee.bookstore.ui.adapter.BookAdapter
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class BookListMvpFragment : MvpAppCompatFragment(), BookListView {

    @InjectPresenter
    lateinit var bookListPresenter: BookListPresenter

    private lateinit var binding: FragmentBookListBinding

    private val adapter =
        BookAdapter { bookItem: BookDto ->
            bookItemClicked(bookItem)
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_list, container, false)
        binding.bookRecyclerView.layoutManager = GridLayoutManager(this.context, 3)
        binding.bookRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.bookRecyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener { bookListPresenter.onRefreshBooks() }
        return binding.root
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
        val intent = Intent(activity, BookDetailsMvpActivity::class.java)
            .apply {
                putExtra(BOOK_ID_EXTRA, bookItem.id)
            }
        startActivity(intent)
    }
}
