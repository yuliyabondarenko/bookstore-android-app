package com.jubee.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.data.StaticData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val books = StaticData.BOOK_ITEMS;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)

        bookRecyclerView.adapter = BookAdapter(books)
    }
}