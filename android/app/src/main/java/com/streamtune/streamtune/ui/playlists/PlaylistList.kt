package com.streamtune.streamtune.ui.playlists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.ui.theme.StreamTuneTheme

@Composable
fun PlaylistList(vm: PlaylistListVM) {

    Scaffold(topBar = { } ,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = vm.onAddPlaylistButtonClick) {
                Icon(Icons.Filled.Add, "Add Playlist")
            }
        },
        content = {

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {

                Text(text = "My Playlists", fontWeight = FontWeight.Bold, fontSize = 48.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(10.dp)
                )

                if(StreamTune.allPlaylists.isEmpty()) {

                    Spacer(Modifier.weight(1f))
                    Text(text = "You have no Playlists!", textAlign = TextAlign.Center, fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(100.dp))
                    Spacer(Modifier.weight(1f))

                } else {

                    LazyColumn {

                        for (playlist in StreamTune.allPlaylists) {
                            item {
                                PlaylistCard(PlaylistCardVM(vm.navController, playlist))
                                HorizontalDivider()
                            }
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