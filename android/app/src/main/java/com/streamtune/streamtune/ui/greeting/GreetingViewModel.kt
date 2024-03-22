package com.streamtune.streamtune.ui.greeting

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

class GreetingViewModel(private val navController: NavController) : ViewModel() {

    val onMainButtonClick: () -> Unit = {
        navController.navigate("songlist")
    }

    val onAuxButtonClick: () -> Unit = {
        navController.navigate("register")
    }

}