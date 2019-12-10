package com.jubee.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.jubee.bookstore.api.BookCollectionApiResponse
import com.jubee.bookstore.model.BookModel
import com.jubee.bookstore.service.NetworkClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookRecyclerView.layoutManager = GridLayoutManager(this, 2)

        NetworkClient.getBookApiService().getBookList(null, null, "price,desc")
            .enqueue(object : Callback<BookCollectionApiResponse> {
                override fun onFailure(call: Call<BookCollectionApiResponse>, t: Throwable) {
                    TODO("not implemented")
                }

                override fun onResponse(
                    call: Call<BookCollectionApiResponse>,
                    response: Response<BookCollectionApiResponse>
                ) {
                    val body = response.body()!!
                    bookRecyclerView.adapter = BookAdapter(
                        body._embedded.books,
                        { bookItem: BookModel -> bookItemClicked(bookItem) })
                }

            })

    }

    private fun bookItemClicked(bookItem: BookModel) {
        Toast.makeText(this, "Clicked: ${bookItem.name}", Toast.LENGTH_LONG).show()
    }

}