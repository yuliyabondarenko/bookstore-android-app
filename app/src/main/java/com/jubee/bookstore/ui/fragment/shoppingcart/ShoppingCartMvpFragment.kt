package com.jubee.bookstore.ui.fragment.shoppingcart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jubee.bookstore.R
import com.jubee.bookstore.app.BookstoreApplication
import com.jubee.bookstore.databinding.FragmentShoppingCartBinding
import com.jubee.bookstore.dto.ShoppingCart
import com.jubee.bookstore.dto.ShoppingCartItem
import com.jubee.bookstore.presentation.mvp.shopcart.ShoppingCartPresenter
import com.jubee.bookstore.presentation.mvp.shopcart.view.ShoppingCartView
import com.jubee.bookstore.ui.fragment.AbstractMvpFragment
import moxy.ktx.moxyPresenter

class ShoppingCartMvpFragment : AbstractMvpFragment<ShoppingCartPresenter>(), ShoppingCartView {

    private val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var binding: FragmentShoppingCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        BookstoreApplication.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shopping_cart,
            container,
            false
        )
        return binding.root
    }


    override fun displayShoppingCart(shoppingCart: ShoppingCart) {
        binding.shoppingCart = shoppingCart
    }

    override fun showError(errorMsg: String?) {
    }

    override fun cleanError() {
    }

    override fun startLoadProgress() {
    }

    override fun stopLoadProgress() {
    }


}
