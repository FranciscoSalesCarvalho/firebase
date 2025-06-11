package com.francisco.firebase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.firebase.domain.repository.AuthRepository
import com.francisco.firebase.domain.repository.Resource
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            when (val result = authRepository.signup(name, email, password)) {
                is Resource.Error -> {
                    when (result.exception) {
                        is FirebaseAuthWeakPasswordException -> "Senha precisa de pelo menos 6 digitos"
                        is FirebaseAuthInvalidCredentialsException -> "E-mail inválido"
                        else -> "Erro desconhecido"
                    }
                }
                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            when(val result = authRepository.login(email, password)) {
                is Resource.Error -> {
                    when (result.exception) {
                        is FirebaseAuthInvalidUserException -> "Email inválido ou desabilitado"
                        is FirebaseAuthInvalidCredentialsException -> "Senha incorreta"
                        else -> "Erro desconhecido"
                    }
                }
                is Resource.Success -> {
                    println(result.data.email)
                }
            }
        }
    }
}