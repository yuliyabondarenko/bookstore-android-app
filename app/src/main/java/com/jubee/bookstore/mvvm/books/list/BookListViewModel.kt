package com.jubee.bookstore.mvvm.books.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubee.bookstore.domain.Failure
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.domain.usecase.BookListUseCase
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.etc.BookstoreError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class BookListViewModel @Inject constructor(private val bookListUseCase: BookListUseCase) :
    ViewModel() {

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
            when (val result = bookListUseCase.getBookList()) {
                is Success -> _booksLiveData.value = result.data
                is Failure -> _errorLiveData.value =
                    BookstoreError(true, "Load books failed. " + result.errorMsg)
            }
            _isLoadingLiveData.value = false
        }
    }

    fun refresh() {
        loadBooks()
    }
}