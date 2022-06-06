package com.example.androidkotlinfinal.features.userdetail

import androidx.lifecycle.*
import com.example.androidkotlinfinal.domain.User
import com.example.androidkotlinfinal.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val userRepository: UserRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d("Error: ${throwable.message}")
    }

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

    private val data: User = state["user"]!!

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess


    fun setup() {
        viewModelScope.launch {
            scope.launch {
                Timber.d("Begin call user")
                userRepository.getUserNetwork(data.login)
                Timber.d("Finished call user")
            }.join()
            scope.launch {
                Timber.d("Begin get user")
                _user.postValue(userRepository.getUserDetail(data.login))
                _isSuccess.postValue(true)
            }
        }
    }
}