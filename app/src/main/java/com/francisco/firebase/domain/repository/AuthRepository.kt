package com.francisco.firebase.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signup(
        name: String,
        email: String,
        password: String
    ): Resource<FirebaseUser>

    suspend fun login(email: String, password: String): Resource<FirebaseUser>

    fun logout()
}

sealed interface Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>
    data class Error(val exception: Exception) : Resource<Nothing>
}