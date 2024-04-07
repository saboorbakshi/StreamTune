package com.streamtune.streamtune.ui.greeting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GreetingViewModel(private val navController: NavController) : ViewModel() {
    // private val tag = "LOGIN ERROR"

    var email: String = ""
    var password: String = ""

    var showToast: (String) -> Unit = {}

    val onAuxButtonClick: () -> Unit = {
        navController.navigate("register")
    }

    val onLogButtonClick: () -> Unit = {
        viewModelScope.launch {
            try {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
                FirebaseAuth.getInstance().currentUser?.getIdToken(true)?.await()?.token?.let { idToken ->
                    if (idToken.isNotEmpty()) {
                        ApiConfig.authToken = idToken
                        ApiCalls.getPlaylists()
                        navController.navigate("playlistlist")
                    } else {
                        showToast("User could not be verified.")
                        // Log.e(tag, "Token ID retrieved was empty.")
                    }
                }
            } catch (e: Exception) {
                showToast(e.localizedMessage ?: "An error occurred.")
                // Log.e(tag, e.localizedMessage as String)
            }
        }
    }
}