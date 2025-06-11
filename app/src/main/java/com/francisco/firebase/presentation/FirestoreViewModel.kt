package com.francisco.firebase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.firebase.data.model.Produto
import com.francisco.firebase.domain.repository.AuthRepository
import com.francisco.firebase.domain.repository.FirestoreRepository
import com.francisco.firebase.domain.repository.Resource
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch

class FirestoreViewModel(
    private val firestoreRepository: FirestoreRepository
): ViewModel() {

    fun getAll() {
        viewModelScope.launch {
            when (val result = firestoreRepository.getAll()) {
                is Resource.Error -> {
                }
                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }

    fun find(id: String) {
        viewModelScope.launch {
            when (val result = firestoreRepository.find(id)) {
                is Resource.Error -> {
                }
                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }

    fun getAllRealTime() {
        viewModelScope.launch {
            when (val result = firestoreRepository.getAllRealTime()) {
                is Resource.Error -> {
                }
                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }

    fun add(produto: Produto) {
        viewModelScope.launch {
            when (val result = firestoreRepository.add(produto)) {
                is Resource.Error -> {
                }
                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }

    fun remove(id: String) {
        viewModelScope.launch {
            when (val result = firestoreRepository.remove(id)) {
                is Resource.Error -> {
                }
                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }
}
