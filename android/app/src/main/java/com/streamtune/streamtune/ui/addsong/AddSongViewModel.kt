package com.streamtune.streamtune.ui.addsong

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    var showToast: (String) -> Unit = {}

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val onAddButtonClick: () -> Unit = {
        val regex = """^(https?://)?(www\.)?(youtube\.com)/.+$""".toRegex()
        if (link.matches(regex)) {
            _isLoading.value = true
            viewModelScope.launch {
                val songID = ApiCalls.addSong(youtubeURL = link)
                if (songID != null) {
                    if (playlistName != ApiConfig.MASTER_PLAYLIST_NAME) {
                        ApiCalls.addToPlaylist(playlistName = playlistName, songID = songID)
                    }
                    ApiCalls.getPlaylists()

                    for (playlist in StreamTune.allPlaylists) {
                        if (playlist.name == playlistName) {
                            StreamTune.allSongs = playlist.songs.toMutableList()
                            break
                        }
                    }
                } else {
                    showToast("Your YouTube link could not be added.")
                }
                withContext(Dispatchers.Main) {
                    navController.navigate("songlist")
                }
                _isLoading.value = false
            }
        } else {
            showToast("$link is not a valid YouTube link.")
        }
    }
}