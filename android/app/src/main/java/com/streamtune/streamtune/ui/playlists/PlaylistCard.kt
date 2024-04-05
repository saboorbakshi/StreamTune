package com.streamtune.streamtune.ui.playlists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.R
import com.streamtune.streamtune.model.Playlist
import com.streamtune.streamtune.ui.theme.StreamTuneTheme

@Composable
fun PlaylistCard(vm: PlaylistCardVM) {

    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        Modifier.clickable(
        interactionSource = interactionSource,
        indication = rememberRipple(color = Color.Black),
        onClick = vm.onClick
    )) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = vm.playlist.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(5.dp))

                val songCount = vm.playlist.songs.size
                Text(
                    "$songCount Song${if (songCount != 1) "s" else ""}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }

            Spacer(Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.delete_forever),
                contentDescription = "Delete Playlist",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { vm.deletePlaylistClick() }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun PlaylistCardPreview() {
    StreamTuneTheme {
        PlaylistCard(PlaylistCardVM(rememberNavController(), Playlist("Cool Songs", emptyList())))
    }
}