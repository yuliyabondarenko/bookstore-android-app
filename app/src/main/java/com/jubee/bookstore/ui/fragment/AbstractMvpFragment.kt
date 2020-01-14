package com.jubee.bookstore.ui.fragment

import moxy.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractMvpFragment<T>  : MvpAppCompatFragment() {

    @Inject
    internal lateinit var presenterProvider: Provider<T>
}