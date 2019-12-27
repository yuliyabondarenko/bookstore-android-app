package com.jubee.bookstore.presentation.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import moxy.MvpPresenter
import moxy.MvpView

abstract class AbstractPresenter<View : MvpView> : MvpPresenter<View>(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    override fun onDestroy() {
        coroutineContext.cancel()
    }
}