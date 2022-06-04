package com.example.androidkotlinfinal.features.userdetail

import androidx.lifecycle.*
import com.example.androidkotlinfinal.domain.User
import com.example.androidkotlinfinal.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val userRepository: UserRepository
) : ViewModel() {

    private val data: User = state["user"]!!

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    init {
        setup()
    }

    private fun setup() {
        viewModelScope.launch {
            if (data.name == null) {
                userRepository.getUserNetwork(data.login)
            }
            val userDatabase = userRepository.getUserDatabase(data.login)
            _user.value = userDatabase
            _isSuccess.value = true
        }
    }
}