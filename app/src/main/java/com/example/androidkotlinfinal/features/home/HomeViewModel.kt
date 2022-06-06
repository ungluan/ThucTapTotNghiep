package com.example.androidkotlinfinal.features.home

import androidx.lifecycle.*
import com.example.androidkotlinfinal.domain.User
import com.example.androidkotlinfinal.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d("Error: ${throwable.message}")
    }

    private val _listUser = userRepository.users
    val listUser: LiveData<List<User>>
        get() = _listUser

    private val _isCompletedRefresh = MutableLiveData<Boolean>()
    val isCompletedRefresh: LiveData<Boolean>
        get() = _isCompletedRefresh

    fun refresherUser() {
        viewModelScope.launch(exceptionHandler) {
            userRepository.refreshUsers()
            _isCompletedRefresh.value = true
        }
    }
}