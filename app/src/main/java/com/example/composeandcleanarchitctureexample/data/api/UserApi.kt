package com.example.composeandcleanarchitctureexample.data.api

import com.example.composeandcleanarchitctureexample.data.model.UserDto
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
