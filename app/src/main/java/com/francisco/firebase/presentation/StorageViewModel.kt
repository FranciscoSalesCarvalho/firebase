package com.francisco.firebase.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.firebase.domain.repository.AuthRepository
import com.francisco.firebase.domain.repository.Resource
import com.francisco.firebase.domain.repository.StorageRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StorageViewModel(
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _imageUiState = MutableStateFlow<String>("")
    val imageUiState = _imageUiState.asStateFlow()

    fun fetchImage(filePath: String) {
        viewModelScope.launch {
            when (val result = storageRepository.getImageDownloadUrl(filePath)) {
                is Resource.Error -> {
                }

                is Resource.Success -> {
                    println(result.data.toString())
                    _imageUiState.value = result.data.toString()
                }
            }
        }
    }

    fun fetchImages(filePath: String) {
        viewModelScope.launch {
            when (val result = storageRepository.listImageUrlsFromPath(filePath)) {
                is Resource.Error -> {
                }

                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }

    fun uploadImageFromAssets(
        context: Context, // Pass context from the Composable
        assetFileName: String,
        storagePath: String = "asset_uploads/", // Default path in Firebase Storage
        targetFileNameInStorage: String? = null
    ) {
        viewModelScope.launch {
            when (val result = storageRepository.uploadAssetImage(
                assetFileName = assetFileName,
                storagePath = storagePath,
                targetFileNameInStorage = targetFileNameInStorage,
                context = context.applicationContext
            )) {
                is Resource.Error -> {
                }

                is Resource.Success -> {
                    println(result.data)
                }
            }
        }
    }
}
