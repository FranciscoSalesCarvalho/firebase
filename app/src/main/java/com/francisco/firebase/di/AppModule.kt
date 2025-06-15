package com.francisco.firebase.di

import com.francisco.firebase.data.repository.AuthRepositoryImpl
import com.francisco.firebase.domain.repository.AuthRepository
import com.francisco.firebase.domain.repository.FirestoreRepository
import com.francisco.firebase.data.repository.FirestoreRepositoryImpl
import com.francisco.firebase.data.repository.StorageRepositoryImpl
import com.francisco.firebase.domain.repository.StorageRepository
import com.francisco.firebase.presentation.AuthViewModel
import com.francisco.firebase.presentation.FirestoreViewModel
import com.francisco.firebase.presentation.StorageViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val firebaseModule = module {
    single<FirebaseAuth> {
        Firebase.auth
    }

    single<FirebaseFirestore> {
        Firebase.firestore
    }

    single<FirebaseStorage> {
        Firebase.storage
    }
}

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<FirestoreRepository> { FirestoreRepositoryImpl(get()) }
    single<StorageRepository> { StorageRepositoryImpl(get()) }

    viewModel {
        AuthViewModel(get())
    }

    viewModel {
        FirestoreViewModel(get())
    }

    viewModel {
        StorageViewModel(get())
    }
}