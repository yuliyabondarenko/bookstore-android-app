package com.jubee.bookstore.presentation.mvp.shopcart

import com.jubee.bookstore.presentation.mvp.AbstractPresenter
import com.jubee.bookstore.presentation.mvp.shopcart.view.ShoppingCartView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ShoppingCartPresenter @Inject constructor(): AbstractPresenter<ShoppingCartView>() {

}
