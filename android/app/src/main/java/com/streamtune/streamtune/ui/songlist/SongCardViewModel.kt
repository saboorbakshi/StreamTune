package com.streamtune.streamtune.ui.songlist

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.model.Song
import com.streamtune.streamtune.ui.playback.PlaybackViewModel

class SongCardViewModel(navController: NavController, val song: Song) : ViewModel() {

    val onClick: () -> Unit = {

        StreamTune.VMStore.playbackVM = PlaybackViewModel(song)

        navController.navigate("playback")

    }

}