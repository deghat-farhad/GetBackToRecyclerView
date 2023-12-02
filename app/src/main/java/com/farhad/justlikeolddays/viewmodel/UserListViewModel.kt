package com.farhad.justlikeolddays.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhad.justlikeolddays.model.User
import com.farhad.justlikeolddays.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel(
    private val userRepository: UserRepository
) : ViewModel(){
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        fetchUser()
    }

    fun fetchUser(){
        viewModelScope.launch {
            _users.postValue(userRepository.getUsers())
        }
    }
}