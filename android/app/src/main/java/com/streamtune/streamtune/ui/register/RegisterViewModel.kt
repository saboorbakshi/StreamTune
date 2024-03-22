package com.streamtune.streamtune.ui.register

import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import com.streamtune.streamtune.ui.AppViewModel
import kotlinx.coroutines.tasks.await

class RegisterViewModel(private val navController: NavController) : AppViewModel() {

    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""

    val onAuxButtonClick: () -> Unit = {
        navController.navigate("greeting")
    }

    val onRegButtonClick: () -> Unit = {
        launchCatching({
            if (password != confirmPassword) {
                throw Exception("Passwords do not match")
            }
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()

            val idTokenResult = Firebase.auth.currentUser?.getIdToken(true)?.await()
            val idToken = idTokenResult?.token

            // Check if the ID token was successfully retrieved
            if (!idToken.isNullOrEmpty()) {
                ApiConfig.authToken = idToken
                navController.navigate("songlist")
            } else {
                Log.e("REGISTER ERROR", "Could not retrieve ID token.")
            }
        }, "REGISTER ERROR")
    }
}