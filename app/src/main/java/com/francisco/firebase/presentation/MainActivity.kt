package com.francisco.firebase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.francisco.firebase.ui.theme.FirebaseTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val authViewModel = getViewModel<AuthViewModel>()
//        viewModel.signup("Francisco", "franklindroosevelt@altostrat.com", "123456")
//        viewModel.login("franklindroosevelt@altostrat.com", "123456")

        val firestoreViewModel = getViewModel<FirestoreViewModel>()
//        firestoreViewModel.getAll()
//        firestoreViewModel.find("cLaLf7uWKBLT4GUqeWDS")
//        firestoreViewModel.getAllRealTime()
//        firestoreViewModel.add(Produto("Novo produto", 77.0))
//        firestoreViewModel.remove("cLaLf7uWKBLT4GUqeWDS")

        val storageViewModel = getViewModel<StorageViewModel>()

        setContent {
            val path by storageViewModel.imageUiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                storageViewModel.fetchImage("kermit.jpg")
                storageViewModel.fetchImages("test")
                storageViewModel.uploadImageFromAssets(
                    context = context,
                    assetFileName = "kermit.jpg",
                    storagePath = "test"
                )
            }

            FirebaseTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(path)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}