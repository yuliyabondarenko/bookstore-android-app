package com.jubee.bookstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.jubee.bookstore.databinding.ActivityBookDetailsBinding
import com.jubee.bookstore.dto.BookDto
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
            .enqueue(object : Callback<BookDto> {
                override fun onResponse(
                    call: Call<BookDto>,
                    response: Response<BookDto>
                ) {
                    binding.book = response.body()
                }

                override fun onFailure(call: Call<BookDto>, t: Throwable) {
                    bookDetailsText.text =
                        getString(R.string.common_error_msg) + t.localizedMessage;
                }

            })
    }
}
