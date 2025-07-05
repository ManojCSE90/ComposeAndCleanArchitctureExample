package com.example.composeandcleanarchitctureexample.domain.usecase

import com.example.composeandcleanarchitctureexample.domain.model.User
import com.example.composeandcleanarchitctureexample.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}
