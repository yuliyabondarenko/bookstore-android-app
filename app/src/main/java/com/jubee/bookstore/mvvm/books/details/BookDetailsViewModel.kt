package com.jubee.bookstore.mvvm.books.details

import android.util.Log
import androidx.lifecycle.*
import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel(private val bookApi: BookApi) : ViewModel() {

    private var bookId: Long = 0

    fun init(bookId: Long) {
        this.bookId = bookId
    }

    private val _bookLiveData: MutableLiveData<BookDto> by lazy {
        MutableLiveData<BookDto>().also { loadBook(bookId) }
    }

    val bookLiveData: LiveData<BookDto>
        get() = _bookLiveData

    private val _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _errorLiveData: MutableLiveData<BookstoreError> = MutableLiveData()

    val errorLiveData: LiveData<BookstoreError>
        get() = _errorLiveData

    private fun loadBook(bookId: Long) {
        _isLoadingLiveData.value = true
        _errorLiveData.value = BookstoreError(false)
        this.viewModelScope.launch(Dispatchers.Main) {
            try {
                _bookLiveData.value = bookApi.getBook(bookId)
            } catch (e: Exception) {
                val errorMsg = "Load book failed"
                _errorLiveData.value = BookstoreError(true, errorMsg)
                Log.e("JB/error", errorMsg, e)
            } finally {
                _isLoadingLiveData.value = false
            }
        }
    }

    class Factory(val bookApi: BookApi) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BookDetailsViewModel(bookApi) as T
        }
    }
}
