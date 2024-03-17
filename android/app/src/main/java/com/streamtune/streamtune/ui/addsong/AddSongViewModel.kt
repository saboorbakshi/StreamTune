package com.streamtune.streamtune.ui.addsong

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class AddSongViewModel(navController: NavController): ViewModel() {

    val onAddSong: () -> Unit = {
        navController.navigate("songlist")
    }
}