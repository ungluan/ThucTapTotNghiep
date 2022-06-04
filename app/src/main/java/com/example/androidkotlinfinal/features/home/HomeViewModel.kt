package com.example.androidkotlinfinal.features.home

import androidx.lifecycle.*
import com.example.androidkotlinfinal.domain.User
import com.example.androidkotlinfinal.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    init {
        refresherUser()
    }

    private val _listUser = userRepository.users
    val listUser: LiveData<List<User>>
        get() = _listUser

    private val _isCompletedRefresh = MutableLiveData<Boolean>()
    val isCompletedRefresh: LiveData<Boolean>
        get() = _isCompletedRefresh

    fun refresherUser() {
        viewModelScope.launch {
            userRepository.refreshUsers()
            withContext(Dispatchers.Main) {
                _isCompletedRefresh.value = true
            }
        }
    }
}