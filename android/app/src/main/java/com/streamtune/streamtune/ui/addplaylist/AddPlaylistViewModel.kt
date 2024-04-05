    package com.streamtune.streamtune.ui.addplaylist

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig

class AddPlaylistViewModel(navController: NavController): ViewModel() {
    var name = ""

    val onAddButtonClick: () -> Unit = {
        // check if duplicate playlist exists!
        ApiCalls.createPlaylist(name = name)
        if (ApiConfig.playlistCreated) {
            ApiCalls.getPlaylists()
            navController.navigate("playlistlist")
            navController.navigate("playlistlist")
        }
    }
}