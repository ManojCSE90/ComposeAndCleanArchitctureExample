package com.example.composeandcleanarchitctureexample.data.repository

import com.example.composeandcleanarchitctureexample.data.api.UserApi
import com.example.composeandcleanarchitctureexample.domain.model.User
import com.example.composeandcleanarchitctureexample.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return api.getUsers().map { it.toDomain() }
    }
}
