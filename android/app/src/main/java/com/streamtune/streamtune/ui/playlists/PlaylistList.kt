package com.streamtune.streamtune.ui.playlists

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.ui.theme.StreamTuneTheme

@Composable
fun PlaylistList(vm: PlaylistListVM) {

    BackHandler {}

    Scaffold(topBar = { } ,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = vm.onAddPlaylistButtonClick, modifier = Modifier.padding(vertical = 18.dp), shape = RoundedCornerShape(100.dp)) {
                Text("Create Playlist", fontSize = 18.sp, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 20.dp))
            }
        },
        content = {

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "My Playlists", fontWeight = FontWeight.Bold, fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Button(
                        onClick = { vm.onLogOutButtonClick() }) {
                        Text("Log Out")
                    }

                }

                LazyColumn {
                    for (playlist in StreamTune.allPlaylists) {
                        item {
                            PlaylistCard(PlaylistCardVM(vm.navController, playlist))
                            HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp))
                        }
                    }

                }

            }

        }

    )

}


@Preview(showBackground = true)
@Composable
private fun SongListPreview() {
    StreamTuneTheme {
        PlaylistList(PlaylistListVM(rememberNavController()))
    }
}