package com.farhad.justlikeolddays.api

import com.farhad.justlikeolddays.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}