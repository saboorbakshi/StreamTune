package com.streamtune.streamtune.ui.playlists

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.model.Playlist
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.ui.songlist.SongListViewModel

class PlaylistCardVM(val navController: NavController, val playlist: Playlist): ViewModel() {

    val onClick: () -> Unit = {

        StreamTune.allSongs = playlist.songs.toMutableList()

        StreamTune.VMStore.songListVM = SongListViewModel(navController, playlist.name)
        navController.navigate("songlist")
    }

    val deletePlaylistClick: () -> Unit = {
        ApiCalls.deletePlaylist(playlist.name)
        ApiCalls.getPlaylists()
        navController.navigate("playlistlist")
    }

}