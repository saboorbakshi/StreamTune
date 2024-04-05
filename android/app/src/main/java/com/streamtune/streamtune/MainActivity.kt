package com.streamtune.streamtune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.ui.addplaylist.AddPlaylist
import com.streamtune.streamtune.ui.addplaylist.AddPlaylistViewModel
import com.streamtune.streamtune.ui.addsong.AddSong
import com.streamtune.streamtune.ui.addsong.AddSongViewModel
import com.streamtune.streamtune.ui.greeting.Greeting
import com.streamtune.streamtune.ui.greeting.GreetingViewModel
import com.streamtune.streamtune.ui.playback.PlaybackScreen
import com.streamtune.streamtune.ui.playlists.PlaylistList
import com.streamtune.streamtune.ui.playlists.PlaylistListVM
import com.streamtune.streamtune.ui.register.Register
import com.streamtune.streamtune.ui.register.RegisterViewModel
import com.streamtune.streamtune.ui.songlist.SongList
import com.streamtune.streamtune.ui.splash.Splash
import com.streamtune.streamtune.ui.splash.SplashViewModel
import com.streamtune.streamtune.ui.theme.StreamTuneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamTuneTheme {
                MainScreen()
            }
        }
    }
}

@Composable
@Preview
private fun MainScreen() {

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "greeting") {
            composable("splash") {
                Splash(SplashViewModel(navController))
            }
            composable("register") {
                Register(RegisterViewModel(navController))
            }
            composable("greeting") {
                Greeting(GreetingViewModel(navController))
            }
            composable("playlistlist") {
                PlaylistList(PlaylistListVM(navController))
            }
            composable("songlist") {
                SongList(StreamTune.VMStore.songListVM)
            }
            composable("addsong") {
                AddSong(StreamTune.VMStore.addSongVM)
            }
            composable("addplaylist") {
                AddPlaylist(AddPlaylistViewModel(navController))
            }
            composable("playback") {
                PlaybackScreen(StreamTune.VMStore.playbackVM)
            }

        }

    }
}
