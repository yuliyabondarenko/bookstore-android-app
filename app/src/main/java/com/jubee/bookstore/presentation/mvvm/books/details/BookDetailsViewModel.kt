package com.jubee.bookstore.presentation.mvvm.books.details

import androidx.lifecycle.*
import com.jubee.bookstore.domain.Failure
import com.jubee.bookstore.domain.Success
import com.jubee.bookstore.domain.usecase.BookDetailsUseCase
import com.jubee.bookstore.dto.BookDto
import com.jubee.bookstore.presentation.ErrorPresence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel(private val bookDetailsUseCase: BookDetailsUseCase) : ViewModel() {

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
            when (val result = bookDetailsUseCase.getBookDetails(bookId)) {
                is Success -> _bookLiveData.value = result.data
                is Failure -> _errorPresenceLiveData.value = ErrorPresence(true, result.errorMsg)
            }
        }
        _isLoadingLiveData.value = false
    }

    class Factory(private val useCase: BookDetailsUseCase) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BookDetailsViewModel(useCase) as T
        }
    }
}
