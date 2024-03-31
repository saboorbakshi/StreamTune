package com.streamtune.streamtune.ui.addsong

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig

class AddSongViewModel(navController: NavController): ViewModel() {
    var link = ""

    val onAddButtonClick: () -> Unit = {
        ApiCalls.addSong(youtubeURL = link)
        if (ApiConfig.songAdded) {
            ApiCalls.getPlaylists()
            navController.navigate("songlist")
        }
    }
}