package com.streamtune.streamtune.ui.greeting

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import com.streamtune.streamtune.ui.AppViewModel
import kotlinx.coroutines.tasks.await
class GreetingViewModel(private val navController: NavController) : AppViewModel() {
    private val tag = "LOGIN"

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

            if (!idToken.isNullOrEmpty()) {
                ApiConfig.authToken = idToken
                ApiCalls.getPlaylists()
                navController.navigate("songlist")
            } else {
                ApiConfig.toast = "We're unable to log you in right now. Please try again later."
                Log.e(tag, "Could not retrieve token ID.")
            }
        }, tag)
    }
}