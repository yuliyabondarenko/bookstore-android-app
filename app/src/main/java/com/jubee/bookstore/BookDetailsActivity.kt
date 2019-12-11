package com.jubee.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.jubee.bookstore.databinding.ActivityBookDetailsBinding
import com.jubee.bookstore.model.BookModel
import com.jubee.bookstore.service.NetworkClient
import kotlinx.android.synthetic.main.activity_book_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_book_details)

        val bookId = intent.getLongExtra(BOOK_ID_EXTRA, 0)

        NetworkClient.getBookApiService().getBook(bookId)
            .enqueue(object : Callback<BookModel> {
                override fun onResponse(
                    call: Call<BookModel>,
                    response: Response<BookModel>
                ) {
                    val bookModel = response.body()
                    binding.book = bookModel
                }

                override fun onFailure(call: Call<BookModel>, t: Throwable) {
                    bookDetailsText.text =
                        getString(R.string.common_error_msg) + t.localizedMessage;
                }

            })
    }
}
