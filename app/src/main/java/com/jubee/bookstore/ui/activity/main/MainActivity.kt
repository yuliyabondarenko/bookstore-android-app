package com.jubee.bookstore.ui.activity.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.navigation.NavigationView
import com.jubee.bookstore.R
import com.jubee.bookstore.app.BookstoreApplication
import com.jubee.bookstore.ui.fragment.books.list.mvp.BookListMvpFragment
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BookListMvvmFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : FragmentActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        BookstoreApplication.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookListMvvm -> displayFragment(BookListMvvmFragment())
            R.id.bookListMvp -> displayFragment(BookListMvpFragment())
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
