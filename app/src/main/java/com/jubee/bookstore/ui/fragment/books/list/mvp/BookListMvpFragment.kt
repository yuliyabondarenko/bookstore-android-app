package com.jubee.bookstore.ui.fragment.books.list.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jubee.bookstore.R
import kotlinx.android.synthetic.main.fragment_book_list_temp.*

class BookListMvpFragment : Fragment(R.layout.fragment_book_list_temp) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textView.text = "Hello from BookListMvpFragment!"

    }
}