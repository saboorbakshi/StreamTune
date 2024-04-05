package com.streamtune.streamtune.ui.addsong

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddSongViewModel(navController: NavController, var playlistName: String): ViewModel() {
    var link = ""

    val onAddButtonClick: () -> Unit = {
        viewModelScope.launch {
            val songID = ApiCalls.addSong(youtubeURL = link)
            if (songID != null && playlistName != "Added Songs") {
                ApiCalls.addToPlaylist(playlistName = playlistName, songID = songID)
            }
            ApiCalls.getPlaylists()

            withContext(Dispatchers.Main) {
                navController.navigate("songlist")
            }
        }
    }
}