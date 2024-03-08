package com.streamtune.streamtune.ui.songlist

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SongListViewModel(navController: NavController): ViewModel() {

    val onAddSongButtonClick: () -> Unit = {
        navController.navigate("addsong")
    }

}