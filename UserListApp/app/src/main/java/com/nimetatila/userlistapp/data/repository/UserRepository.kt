package com.nimetatila.userlistapp.data.repository

import com.nimetatila.userlistapp.data.model.User
import com.nimetatila.userlistapp.data.remote.RetrofitInstance

class UserRepository {

    suspend fun getUsers(): List<User> {
        return RetrofitInstance.api.getUsers()
    }
}