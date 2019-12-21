package com.jubee.bookstore.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import com.jubee.bookstore.R
import com.jubee.bookstore.ui.activity.books.list.mvp.BookListMvpActivity
import com.jubee.bookstore.ui.activity.books.list.mvvm.BookListMvvmActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookListMvvm -> startActivity(Intent(this, BookListMvvmActivity::class.java))
            R.id.bookListMvp -> startActivity(Intent(this, BookListMvpActivity::class.java))
        }
        return true
    }
}
