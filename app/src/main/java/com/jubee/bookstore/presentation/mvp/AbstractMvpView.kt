package com.jubee.bookstore.presentation.mvp

import moxy.MvpView

interface AbstractMvpView : MvpView {

    fun showError(errorMsg: String?)

    fun cleanError()

    fun startLoadProgress()

    fun stopLoadProgress()

}