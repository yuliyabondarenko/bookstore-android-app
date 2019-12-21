package com.jubee.bookstore.ui.fragment.books.list.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jubee.bookstore.R
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListMvvmFragment : Fragment(R.layout.fragment_book_list) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        helloFragmentText.text = "Hello from Book List MVVM Fragment"
    }
}