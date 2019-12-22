package com.jubee.bookstore.ui.fragment.books.list.mvvm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.R
import com.jubee.bookstore.databinding.FragmentBookListBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.mvvm.list.BookListViewModel
import com.jubee.bookstore.ui.activity.books.details.mvvm.BookDetailsMvvmActivity
import com.jubee.bookstore.ui.adapter.BookAdapter


const val BOOK_ID_EXTRA = "com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA"

class BookListMvvFragment : Fragment() {

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

        binding.bookRecyclerView.adapter = adapter
        binding.bookRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.bookRecyclerView.itemAnimator = DefaultItemAnimator()

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

        binding.swipeRefresh.setOnRefreshListener { bookListViewModel.refresh() }

        return binding.root
    }


    private fun bookItemClicked(bookItem: BookDto) {
        val intent = Intent(activity, BookDetailsMvvmActivity::class.java)
            .apply {
                putExtra(BOOK_ID_EXTRA, bookItem.id)
            }
        startActivity(intent)
    }

}