package com.example.newsneat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newsneat.MyViewModel.MyViewModel
import com.example.newsneat.screens.nav.NavApp
import com.example.newsneat.ui.theme.NewsNeatTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsNeatTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    NavApp(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = MyViewModel(),
                        padding = innerPadding
                    )
                }
            }
        }
    }
}

