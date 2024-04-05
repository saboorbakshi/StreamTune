package com.streamtune.streamtune.ui.splash

import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamtune.streamtune.ui.AppViewModel

class SplashViewModel(private val navController: NavController) : AppViewModel() {
    fun onAppStart() {
        if (Firebase.auth.currentUser != null) navController.navigate("playlistlist")
        else navController.navigate("greeting")
    }
}