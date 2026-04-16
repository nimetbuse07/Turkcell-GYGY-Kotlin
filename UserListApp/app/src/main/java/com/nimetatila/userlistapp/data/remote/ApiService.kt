package com.nimetatila.userlistapp.data.remote

import com.nimetatila.userlistapp.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers() : List<User>
}