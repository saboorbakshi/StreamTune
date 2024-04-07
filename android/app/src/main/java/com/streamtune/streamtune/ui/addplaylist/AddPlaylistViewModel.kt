    package com.streamtune.streamtune.ui.addplaylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.network.ApiCalls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

    class AddPlaylistViewModel(navController: NavController): ViewModel() {
    var name = ""
    var showToast: (String) -> Unit = {}

    val onAddButtonClick: () -> Unit = {
        var noDuplicates = true
        for (playlist in StreamTune.allPlaylists) {
            if (playlist.name == name) {
                noDuplicates = false
                break
            }
        }
        if (noDuplicates) {
            viewModelScope.launch {
                ApiCalls.createPlaylist(name = name)
                ApiCalls.getPlaylists()

                withContext(Dispatchers.Main) {
                    navController.navigate("playlistlist")
                }
            }
        } else {
            showToast("$name already exists. Try using another name.")
        }
    }
}