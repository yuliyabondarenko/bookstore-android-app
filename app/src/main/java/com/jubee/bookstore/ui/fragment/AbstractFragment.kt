package com.jubee.bookstore.ui.fragment

import moxy.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractFragment<T>  : MvpAppCompatFragment() {

    @Inject
    internal lateinit var presenterProvider: Provider<T>
}