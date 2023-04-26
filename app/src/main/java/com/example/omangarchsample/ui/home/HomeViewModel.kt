package com.example.omangarchsample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omangarchsample.NetworkResult
import com.example.omangarchsample.model.database.User
import com.example.omangarchsample.model.remote.UserApiModel
import com.example.omangarchsample.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _usersList = MutableLiveData<List<User>>(listOf())
    val userList: LiveData<List<User>>
        get() = _usersList


    fun fetchUser() {
        viewModelScope.launch {
            userRepository.fetchUsers("1").collect {
                when (it) {
                    is NetworkResult.Error -> {

                    }

                    is NetworkResult.Failure -> {

                    }

                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {
                        saveUser(it.data!!)
                    }
                }


            }
        }

    }

    private fun saveUser(userApiModelList: List<UserApiModel>) {
        viewModelScope.launch {
            userRepository.saveUser(userApiModelList)
            _usersList.value = userRepository.getAllUsers()
        }

    }
}