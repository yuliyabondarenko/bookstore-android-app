package com.jubee.bookstore.ui.fragment.books.list.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.R
import com.jubee.bookstore.app.BookstoreApplication
import com.jubee.bookstore.databinding.FragmentBookListBinding
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.ErrorPresence
import com.jubee.bookstore.presentation.mvvm.books.list.BookListViewModel
import com.jubee.bookstore.ui.adapter.BookAdapter
import com.jubee.bookstore.ui.fragment.books.details.mvvm.BookDetailsMvvmFragment
import com.jubee.bookstore.ui.fragment.books.transition.setUpTransition
import javax.inject.Inject


const val BOOK_ID_EXTRA = "com.jubee.bookstore.ui.fragment.books.list.mvvm.BOOK_ID_EXTRA"

class BookListMvvmFragment : Fragment() {

    @Inject
    lateinit var bookListViewModel: BookListViewModel

    private lateinit var binding: FragmentBookListBinding

    private val adapter =
        BookAdapter { viewHolder, bookItem -> bookItemClicked(viewHolder, bookItem) }

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

        binding.bookRecyclerView.adapter = adapter
        binding.bookRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.bookRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.swipeRefresh.setOnRefreshListener { bookListViewModel.refresh() }

        setUpObservers(bookListViewModel)
        return binding.root
    }

    private fun setUpObservers(bookListViewModel: BookListViewModel) {
        bookListViewModel.booksLiveData.observe(this, Observer<List<BookDto>> { books ->
            adapter.books = books.toMutableList()
        })

        bookListViewModel.isLoadingLiveData.observe(this, Observer<Boolean> { isLoading ->
            binding.isLoading = isLoading
        })

        bookListViewModel.errorPresenceLiveData.observe(this, Observer<ErrorPresence> { error ->
            binding.error = error
        })
    }


    private fun bookItemClicked(bookItem: BookDto, itemView: View) {
        val detailsFragment = BookDetailsMvvmFragment().apply {
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