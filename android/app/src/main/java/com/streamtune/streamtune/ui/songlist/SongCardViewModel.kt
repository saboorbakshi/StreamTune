package com.streamtune.streamtune.ui.songlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.model.Song
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.ui.playback.PlaybackViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongCardViewModel(navController: NavController, private val playlistName: String, val song: Song) : ViewModel() {

    val onClick: () -> Unit = {
        StreamTune.VMStore.playbackVM = PlaybackViewModel(song)
        navController.navigate("playback")
    }

    val deleteSongClick: () -> Unit = {
        viewModelScope.launch {
            ApiCalls.deleteFromPlaylist(playlistName = playlistName, songID = song.id)
            ApiCalls.getPlaylists()

            for (playlist in StreamTune.allPlaylists) {
                if (playlist.name == playlistName) {
                    StreamTune.allSongs = playlist.songs.toMutableList()
                    break;
                }
            }

            withContext(Dispatchers.Main) {
                navController.navigate("songlist")
            }
        }
    }
}