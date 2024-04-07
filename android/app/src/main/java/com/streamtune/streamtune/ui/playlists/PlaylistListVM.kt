package com.streamtune.streamtune.ui.playlists

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class PlaylistListVM(val navController: NavController): ViewModel() {

    val onLogOutButtonClick: () -> Unit = {
        FirebaseAuth.getInstance().signOut()
        navController.navigate("greeting")
    }

    val onAddPlaylistButtonClick: () -> Unit = {
        navController.navigate("addplaylist")
    }

}