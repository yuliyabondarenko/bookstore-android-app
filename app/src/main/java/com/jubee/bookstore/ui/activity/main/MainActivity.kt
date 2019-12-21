package com.jubee.bookstore.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.navigation.NavigationView
import com.jubee.bookstore.R
import com.jubee.bookstore.ui.activity.books.list.mvp.BookListMvpActivity
import com.jubee.bookstore.ui.activity.books.list.mvvm.BookListMvvmActivity
import com.jubee.bookstore.ui.fragment.books.list.mvp.BookListMvpFragment
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BookListMvvmFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : FragmentActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookListMvvm -> startActivity(Intent(this, BookListMvvmActivity::class.java))
            R.id.bookListMvp -> startActivity(Intent(this, BookListMvpActivity::class.java))
            R.id.bookListMvvmFragment -> displayFragment(BookListMvvmFragment())
            R.id.bookListMvpFragment -> displayFragment(BookListMvpFragment())
        }
        return super.onOptionsItemSelected(item)

    }

    private fun displayFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFragmentContainer, fragment)
        fragmentTransaction.commit()

        drawer.closeDrawers();
    }

}
