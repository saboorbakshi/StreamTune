package com.streamtune.streamtune.ui.greeting

import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import com.streamtune.streamtune.ui.AppViewModel
import kotlinx.coroutines.tasks.await

class GreetingViewModel(private val navController: NavController) : AppViewModel() {

    var email: String = ""
    var password: String = ""

    val onAuxButtonClick: () -> Unit = {
        navController.navigate("register")
    }

    val onLogButtonClick: () -> Unit = {
        launchCatching({
            Firebase.auth.signInWithEmailAndPassword(email, password).await()

            val idTokenResult = Firebase.auth.currentUser?.getIdToken(false)?.await()
            val idToken = idTokenResult?.token

            // Check if the ID token was successfully retrieved
            if (!idToken.isNullOrEmpty()) {
                ApiConfig.authToken = idToken
                navController.navigate("songlist")
            } else {
                Log.e("LOGIN ERROR", "Could not retrieve ID token.")
            }
        }, "LOGIN ERROR")
    }
}