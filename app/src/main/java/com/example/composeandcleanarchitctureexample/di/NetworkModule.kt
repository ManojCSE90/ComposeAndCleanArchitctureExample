package com.example.composeandcleanarchitctureexample.di

import com.example.composeandcleanarchitctureexample.data.api.UserApi
import com.example.composeandcleanarchitctureexample.data.repository.UserRepositoryImpl
import com.example.composeandcleanarchitctureexample.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    fun provideRepository(api: UserApi): UserRepository {
        return UserRepositoryImpl(api)
    }
}
