package com.streamtune.streamtune.ui.songlist

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.streamtune.streamtune.R
import com.streamtune.streamtune.model.Song
import com.streamtune.streamtune.ui.theme.StreamTuneTheme


@Composable
fun SongCard(vm: SongCardViewModel) {

    val interactionSource = remember { MutableInteractionSource() }
    Surface(Modifier.clickable(
        interactionSource = interactionSource,
        indication = rememberRipple(color = Color.Black),
        onClick = vm.onClick
    )) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            AsyncImage(
                model = vm.song.albumCover,
                contentDescription = "Album Art",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(Modifier.width(10.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = vm.song.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    vm.song.artist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.delete_forever),
                contentDescription = "Delete Song",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { vm.deleteSongClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SongCardPreview() {
    StreamTuneTheme {
        SongCard(SongCardViewModel(rememberNavController(),
            Song("70", "sndo;fsandfwendiew'asddwed", "DSFNWDO;FNWEIDJC S.DFNSDAWEDRNDF", "", "https://upload.wikimedia.org/wikipedia/en/b/b0/Yoasobi_-_Idol.png", 120)
        ))
    }
}