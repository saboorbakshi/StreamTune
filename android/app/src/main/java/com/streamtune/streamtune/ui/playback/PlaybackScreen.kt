package com.streamtune.streamtune.ui.playback

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.streamtune.streamtune.R
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.ui.theme.StreamTuneTheme
import kotlinx.coroutines.delay

@Composable
fun PlaybackScreen(vm: PlaybackViewModel) {

    Surface(Modifier.fillMaxSize()) {

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Image(
                painter = painterResource(id = R.drawable.yoasobi_the_book),
                contentDescription = "AlbumArt",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
            )

            Spacer(Modifier.height(10.dp))

            Text(text = vm.song.title, fontWeight = FontWeight.Bold, fontSize = 36.sp)

            Spacer(Modifier.height(10.dp))

            Text(vm.song.artist, fontWeight = FontWeight.Medium, fontSize = 18.sp)


            Spacer(Modifier.height(25.dp))

            var sliderPosition by remember { mutableFloatStateOf(0f) }
            val songDuration by vm.songDuration.collectAsState()

            LaunchedEffect(key1 = vm.player.currentPosition, key2 = vm.player.isPlaying) {
                delay(1000)
                sliderPosition = (vm.player.currentPosition.toFloat() / 1000)
            }

            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                onValueChangeFinished = {
                    vm.player.seekTo(sliderPosition.toLong())
                },
                valueRange = 0f..songDuration,
                modifier = Modifier.padding(40.dp, 0.dp)
            )

            Row(Modifier.padding(40.dp, 0.dp).fillMaxWidth()) {
                Text(text = vm.secondsToTimestamp(sliderPosition), fontWeight = FontWeight.Medium)
                Spacer(Modifier.weight(1f))
                Text(text = vm.secondsToTimestamp(songDuration), fontWeight = FontWeight.Medium)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(id = R.drawable.shuffle),
                    contentDescription = "Shuffle",
                    modifier = Modifier.size(30.dp)
                )

                Spacer(Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.skip_prev),
                    contentDescription = "Skip Previous",
                    modifier = Modifier.size(80.dp)
                )

                var isPlaying by remember { mutableStateOf(false) }
                Image(
                    painter = painterResource(id = if(isPlaying) R.drawable.pause else R.drawable.play),
                    contentDescription = "Play Button",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            if(isPlaying) {
                                isPlaying = false
                                vm.player.pause()
                            } else {
                                isPlaying = true
                                vm.player.play()
                            }
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.skip_next),
                    contentDescription = "Skip Next",
                    modifier = Modifier.size(80.dp)
                )

                Spacer(Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.repeat),
                    contentDescription = "Repeat",
                    modifier = Modifier.size(30.dp)
                )

            }

        }

    }

}


@Preview
@Composable
private fun PlaybackPreview() {
    StreamTuneTheme {
        PlaybackScreen(PlaybackViewModel(StreamTune.allSongs[0]))
    }
}