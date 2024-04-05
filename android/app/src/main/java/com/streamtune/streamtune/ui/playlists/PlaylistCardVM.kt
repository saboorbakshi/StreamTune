package com.streamtune.streamtune.ui.playlists

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.model.Playlist
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.ui.songlist.SongListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaylistCardVM(val navController: NavController, val playlist: Playlist): ViewModel() {

    val onClick: () -> Unit = {
        StreamTune.allSongs = playlist.songs.toMutableList()
        StreamTune.VMStore.songListVM = SongListViewModel(navController, playlist.name)
        navController.navigate("songlist")
    }

    val deletePlaylistClick: () -> Unit = {
        viewModelScope.launch {
            ApiCalls.deletePlaylist(playlist.name)
            ApiCalls.getPlaylists()

            withContext(Dispatchers.Main) {
                navController.navigate("playlistlist")
            }
        }
    }
}