package com.streamtune.streamtune.ui.splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase
import kotlinx.coroutines.tasks.await
import com.streamtune.streamtune.ui.AppViewModel

class SplashViewModel(private val navController: NavController) : AppViewModel() {
    fun onAppStart() {
        if (Firebase.auth.currentUser != null) navController.navigate("songlist")
        else navController.navigate("greeting")
    }
}