package com.streamtune.streamtune.ui.songlist

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.ui.addsong.AddSongViewModel

class SongListViewModel(val navController: NavController, val playlistName: String): ViewModel() {

    val onAddSongButtonClick: () -> Unit = {
        StreamTune.VMStore.addSongVM = AddSongViewModel(navController, playlistName)
        navController.navigate("addsong")
    }

}