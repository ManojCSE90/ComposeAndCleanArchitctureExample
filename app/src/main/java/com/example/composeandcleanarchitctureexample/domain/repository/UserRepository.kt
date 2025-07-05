package com.example.composeandcleanarchitctureexample.domain.repository

import com.example.composeandcleanarchitctureexample.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
