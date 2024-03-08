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
import com.streamtune.streamtune.ui.AddSong
import com.streamtune.streamtune.ui.greeting.Greeting
import com.streamtune.streamtune.ui.greeting.GreetingViewModel
import com.streamtune.streamtune.ui.songlist.SongList
import com.streamtune.streamtune.ui.songlist.SongListViewModel
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

    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "greeting") {
            composable("greeting") {
                Greeting(GreetingViewModel(navController))
            }
            composable("songlist") {
                SongList(SongListViewModel(navController))
            }
            composable("addsong") {
                AddSong()
            }
        }

    }

}