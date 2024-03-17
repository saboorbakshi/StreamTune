package com.streamtune.streamtune.ui.register

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterViewModel(private val navController: NavController) : ViewModel() {

    val onRegButtonClick: () -> Unit = {
        navController.navigate("songlist")
    }

    val onAuxButtonClick: () -> Unit = {
        navController.navigate("greeting")
    }

}