package com.streamtune.streamtune.ui.greeting

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

class GreetingViewModel(private val navController: NavController) : ViewModel() {

    var isLoginMode: Boolean = true

    // UI Labels
    var title: MutableStateFlow<String> = MutableStateFlow("Login")
    var mainButtonLabel: MutableStateFlow<String> = MutableStateFlow("Login")
    var auxButtonLabel: MutableStateFlow<String> = MutableStateFlow("No Account? Sign Up!")


    val onMainButtonClick: () -> Unit = {
        navController.navigate("songlist")
    }

    val onAuxButtonClick: () -> Unit = {
        isLoginMode = !isLoginMode

        title.value = if (isLoginMode) "Login" else "Sign Up"
        mainButtonLabel.value = if (isLoginMode) "Login" else "Sign Up"
        auxButtonLabel.value = if(isLoginMode) "No Account? Sign Up!" else "Already Have an Account? Log In"
    }

}