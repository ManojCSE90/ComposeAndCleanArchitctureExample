package com.example.composeandcleanarchitctureexample.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeandcleanarchitctureexample.domain.model.User
import com.example.composeandcleanarchitctureexample.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _users.value = getUsersUseCase()
            }catch (e: Exception){

                Log.d("TAG", "fetchUsers: ${e.message}")
            }

        }
    }
}
