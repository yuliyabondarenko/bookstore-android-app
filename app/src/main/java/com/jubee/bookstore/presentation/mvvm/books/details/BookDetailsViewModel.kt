package com.jubee.bookstore.presentation.mvvm.books.details

import android.util.Log
import androidx.lifecycle.*
import com.jubee.bookstore.api.BookApi
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.ErrorPresence
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

    private val _errorPresenceLiveData: MutableLiveData<ErrorPresence> = MutableLiveData()

    val errorPresenceLiveData: LiveData<ErrorPresence>
        get() = _errorPresenceLiveData

    private fun loadBook(bookId: Long) {
        _isLoadingLiveData.value = true
        _errorPresenceLiveData.value = ErrorPresence(false)
        this.viewModelScope.launch(Dispatchers.Main) {
            try {
                _bookLiveData.value = bookApi.getBook(bookId)
            } catch (e: Exception) {
                val errorMsg = "Load book failed"
                _errorPresenceLiveData.value = ErrorPresence(true, errorMsg)
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
