package com.example.composeandcleanarchitctureexample.data.model

import com.example.composeandcleanarchitctureexample.domain.model.User

data class UserDto(
    val id: Int,
    val name: String,
    val email: String
) {
    fun toDomain(): User = User(id, name, email)
}

