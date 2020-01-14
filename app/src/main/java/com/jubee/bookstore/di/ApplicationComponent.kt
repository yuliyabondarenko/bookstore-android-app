package com.jubee.bookstore.di

import com.jubee.bookstore.di.module.NetworkModule
import com.jubee.bookstore.di.module.PersistenceModule
import com.jubee.bookstore.di.module.ViewModelModule
import com.jubee.bookstore.ui.activity.main.MainActivity
import com.jubee.bookstore.ui.fragment.books.details.mvp.BookDetailsMvpFragment
import com.jubee.bookstore.ui.fragment.books.details.mvvm.BookDetailsMvvmFragment
import com.jubee.bookstore.ui.fragment.books.list.mvp.BookListMvpFragment
import com.jubee.bookstore.ui.fragment.books.list.mvvm.BookListMvvmFragment
import com.jubee.bookstore.ui.fragment.shoppingcart.ShoppingCartMvpFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        NetworkModule::class,
        PersistenceModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: BookListMvvmFragment)
    fun inject(fragment: BookListMvpFragment)
    fun inject(fragment: BookDetailsMvpFragment)
    fun inject(fragment: BookDetailsMvvmFragment)
    fun inject(fragment: ShoppingCartMvpFragment)
}