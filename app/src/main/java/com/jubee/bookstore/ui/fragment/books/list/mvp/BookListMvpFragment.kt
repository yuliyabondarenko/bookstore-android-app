package com.jubee.bookstore.ui.fragment.books.list.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.R
import com.jubee.bookstore.app.BookstoreApplication
import com.jubee.bookstore.databinding.FragmentBookListBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.ErrorPresence
import com.jubee.bookstore.presentation.mvp.books.list.BookListPresenter
import com.jubee.bookstore.presentation.mvp.books.list.view.BookListView
import com.jubee.bookstore.ui.fragment.books.details.mvp.BookDetailsMvpFragment
import com.jubee.bookstore.ui.adapter.BookAdapter
import com.jubee.bookstore.ui.fragment.AbstractFragment
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA
import com.jubee.bookstore.ui.fragment.books.transition.setUpTransition
import moxy.ktx.moxyPresenter


class BookListMvpFragment : AbstractFragment<BookListPresenter>(), BookListView {

    private val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var binding: FragmentBookListBinding

    private val adapter =
        BookAdapter { bookItem, itemView -> bookItemClicked(bookItem, itemView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        BookstoreApplication.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
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

        binding.swipeRefresh.setOnRefreshListener { presenter.onRefreshBooks() }
        return binding.root
    }

    override fun startLoadProgress() {
        binding.isLoading = true
    }

    override fun stopLoadProgress() {
        binding.isLoading = false
    }

    override fun displayBooks(books: List<BookDto>) {
        adapter.books = books.toMutableList()
    }

    override fun showError(errorMsg: String) {
        binding.error = ErrorPresence(true, errorMsg)
    }

    override fun cleanError() {
        binding.error = ErrorPresence(false)
    }

    private fun bookItemClicked(bookItem: BookDto, itemView: View) {
        val detailsFragment = BookDetailsMvpFragment().apply {
            arguments = Bundle().apply { putLong(BOOK_ID_EXTRA, bookItem.id) }
        }

        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragmentContainer, detailsFragment)
            .addToBackStack(null)
            .setUpTransition(this, detailsFragment, bookItem.id, itemView)
            .commit()
    }
}
