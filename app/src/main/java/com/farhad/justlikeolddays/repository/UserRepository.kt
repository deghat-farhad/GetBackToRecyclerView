package com.farhad.justlikeolddays.repository

import com.farhad.justlikeolddays.api.ApiService

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}