package com.jubee.bookstore.mvvm.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import com.jubee.bookstore.service.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BookListViewModel : ViewModel() {
    private val bookApi = NetworkService.bookApi

    private val _booksLiveData: MutableLiveData<List<BookDto>> by lazy {
        MutableLiveData<List<BookDto>>().also {
            loadBooks()
        }
    }

    val booksLiveData: LiveData<List<BookDto>>
        get() = _booksLiveData

    private val _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _errorLiveData: MutableLiveData<BookstoreError> = MutableLiveData()

    val errorLiveData: LiveData<BookstoreError>
        get() = _errorLiveData

    private fun loadBooks() {
        _isLoadingLiveData.value = true
        _errorLiveData.value = BookstoreError(false)
        this.viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = bookApi.getBookList()
                _booksLiveData.value = response._embedded.books
            } catch (e: Exception) {
                val errorMsg = "Load books failed"
                _errorLiveData.value = BookstoreError(true, errorMsg)
                Log.e("JB/error", errorMsg, e)
            } finally {
                _isLoadingLiveData.value = false
            }
        }
    }

    fun refresh() {
        loadBooks()
    }
}