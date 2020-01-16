package com.jubee.bookstore.presentation.mvp.shopcart.view

import com.jubee.bookstore.dto.ShoppingCart
import com.jubee.bookstore.presentation.mvp.AbstractMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ShoppingCartView : AbstractMvpView {

    fun displayShoppingCart(shoppingCart: ShoppingCart)

}
