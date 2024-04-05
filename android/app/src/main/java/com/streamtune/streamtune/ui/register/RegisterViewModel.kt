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
    private val tag = "REGISTER"
    private val mismatchMsg = "Passwords do not match"

    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""

    val onAuxButtonClick: () -> Unit = {
        navController.navigate("greeting")
    }

    val onRegButtonClick: () -> Unit = {
        launchCatching({
            if (password != confirmPassword) {
                ApiConfig.toast = mismatchMsg
                throw Exception(mismatchMsg)
            }
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            val idTokenResult = Firebase.auth.currentUser?.getIdToken(true)?.await()
            val idToken = idTokenResult?.token

            if (!idToken.isNullOrEmpty()) {
                ApiConfig.authToken = idToken
                ApiCalls.getPlaylists()
                navController.navigate("playlistlist")
            } else {
                ApiConfig.toast = "We're unable to register you right now. Please try again later."
                Log.e(tag, "Could not retrieve ID token.")
            }
        }, tag)
    }
}