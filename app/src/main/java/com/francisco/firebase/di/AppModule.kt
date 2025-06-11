package com.francisco.firebase.di

import com.francisco.firebase.data.repository.AuthRepositoryImpl
import com.francisco.firebase.domain.repository.AuthRepository
import com.francisco.firebase.presentation.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firebaseModule = module {
    single<FirebaseAuth> {
        Firebase.auth
    }
}

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    viewModel {
        AuthViewModel(get())
    }
}