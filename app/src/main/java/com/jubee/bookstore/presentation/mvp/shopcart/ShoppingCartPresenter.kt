package com.jubee.bookstore.presentation.mvp.shopcart

import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.dto.ShoppingCart
import com.jubee.bookstore.dto.ShoppingCartItem
import com.jubee.bookstore.persistence.dao.BookDao
import com.jubee.bookstore.presentation.mvp.AbstractPresenter
import com.jubee.bookstore.presentation.mvp.shopcart.StaticData.BOOK_ITEMS
import com.jubee.bookstore.presentation.mvp.shopcart.view.ShoppingCartView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ShoppingCartPresenter @Inject constructor(
    private val bookDao: BookDao
) :
    AbstractPresenter<ShoppingCartView>() {

    override fun onFirstViewAttach() {
        loadShoppingCart()
    }

    private fun loadShoppingCart() {
        viewState.startLoadProgress()
        viewState.cleanError()

        val items = mockItems()
        val shoppingCart = ShoppingCart(items, calculateCost(items))
        viewState.displayShoppingCart(shoppingCart)

        viewState.stopLoadProgress()
    }

    private fun mockItems(): List<ShoppingCartItem> {
        return listOf(
            ShoppingCartItem(BOOK_ITEMS[0], 1)
            , ShoppingCartItem(BOOK_ITEMS[1], 2)
            , ShoppingCartItem(BOOK_ITEMS[2], 3)
            , ShoppingCartItem(BOOK_ITEMS[3], 4)
        )
    }

    private fun calculateCost(items: List<ShoppingCartItem>): Double {
        return items.map { item -> item.count * item.book.price!! }.sum()
    }

}


object StaticData {
    var BOOK_ITEMS: List<BookDto> = listOf(
        BookDto(
            1,
            "Юлий Алкин \"Цена познания\"",
            "http://fb2-epub.ru/Fantstic/Alkin/Tsiena_poznaniia-Iurii_Alkin.jpg",
            false,
            12.3
        ),
        BookDto(
            2,
            "Оноре де Бальзак \"Тридцатилетняя женщина\"",
            "http://irecommend.ru/sites/default/files/product-images/75276/ZtWWyVa21Kvl4bYJFfvg.jpg",
            true,
            10.2
        ),
        BookDto(
            3,
            "Трейси Брайан \"Оставьте брезгливость, съешьте лягушку!\"",
            "https://erudyt.com.ua/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/0/1004187.jpg",
            true,
            17.4
        ),
        BookDto(
            4,
            "Рей Бредбери - \"451 градус по Фаренгейту\"",
            "http://loveread.ec/img/photo_books/2039.jpg",
            true,
            53.34
        ),
        BookDto(
            5,
            "Оскар Уайльд - \"Портрет Дориана Грея\"",
            "https://i1.rozetka.ua/goods/2449872/28944049_images_2449872889.jpg",
            true,
            54.4
        ),
        BookDto(
            6,
            "нтуан Франсуа Прево - \"История кавалера де Грие и Манон Леско\"",
            "https://i.livelib.ru/boocover/1000025434/200/ea35/AntuanFransua_Prevo__Istoriya_kavalera_de_Grie_i_Manon_Lesko.jpg",
            true,
            11.44
        )
    )
}