package com.streamtune.streamtune.ui.addsong

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig

class AddSongViewModel(navController: NavController, var playlistName: String): ViewModel() {
    var link = ""
    private val mainPlaylistName = "Added Songs"

    val onAddButtonClick: () -> Unit = {
        ApiCalls.addSong(youtubeURL = link)

        ApiCalls.getPlaylists()    // updates StreamTune.allPlaylists

        var songID = ""
        StreamTune.allPlaylists.forEach { playlist ->
            if (playlist.name == mainPlaylistName) {
                playlist.songs.forEach { song ->
                    if (song.url == link) {
                        songID = song.id
                    }
                }
            }
        }
        Log.i("SONG ID", songID)
        if (songID != "") {
            ApiCalls.addToPlaylist(playlistName = playlistName, songID = songID)
            ApiCalls.getPlaylists()               // updates StreamTune.allPlaylists
        }
        navController.navigate("songlist")
    }
}