package com.streamtune.streamtune.ui.addsong

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.streamtune.streamtune.network.ApiCalls

class AddSongViewModel(navController: NavController): ViewModel() {
    var link = ""

    val onAddButtonClick: () -> Unit = {
        ApiCalls.postSongRequest(youtubeURL = link)
        navController.navigate("songlist")
    }
}