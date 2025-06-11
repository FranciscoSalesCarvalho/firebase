package com.francisco.firebase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.francisco.firebase.data.model.Produto
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
        firestoreViewModel.remove("cLaLf7uWKBLT4GUqeWDS")

        setContent {
            FirebaseTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}