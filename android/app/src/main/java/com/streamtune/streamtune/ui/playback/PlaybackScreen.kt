package com.streamtune.streamtune.ui.playback

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.streamtune.streamtune.R
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.ui.theme.StreamTuneTheme
import kotlinx.coroutines.delay

@Composable
fun PlaybackScreen(vm: PlaybackViewModel) {

    Surface(Modifier.fillMaxSize()) {

        val nowPlaying by vm.nowPlaying.collectAsState()

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            AsyncImage(
                model = nowPlaying.albumCover,
                contentDescription = "AlbumArt",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
            )

            Spacer(Modifier.height(10.dp))

            Text(text = nowPlaying.title, fontWeight = FontWeight.Medium, fontSize = 36.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 40.dp)
            )

            Spacer(Modifier.height(10.dp))

            Text(nowPlaying.artist, fontWeight = FontWeight.Medium, fontSize = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 40.dp)
            )

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
                    vm.player.seekTo(sliderPosition.toLong() * 1000L)
                },
                valueRange = 0f..songDuration,
                modifier = Modifier.padding(40.dp, 0.dp)
            )

            Row(
                Modifier
                    .padding(40.dp, 0.dp)
                    .fillMaxWidth()) {
                Text(text = vm.secondsToTimestamp(sliderPosition), fontWeight = FontWeight.Medium)
                Spacer(Modifier.weight(1f))
                Text(text = vm.secondsToTimestamp(songDuration), fontWeight = FontWeight.Medium)
            }

            var shuffleColor by remember { mutableStateOf(Color.LightGray) }
            var repeatColor by remember { mutableStateOf(Color.LightGray) }

            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(id = R.drawable.shuffle),
                    contentDescription = "Shuffle",
                    colorFilter = ColorFilter.tint(shuffleColor),
                    modifier = Modifier.size(30.dp).clickable {
                        if(vm.playableList.shuffle) {
                            vm.playableList.shuffle = false
                            shuffleColor = Color.LightGray
                        } else {
                            vm.playableList.shuffle = true
                            vm.playableList.repeat = false
                            shuffleColor = Color.Black
                            repeatColor = Color.LightGray
                        }
                    }
                )

                Spacer(Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.skip_prev),
                    contentDescription = "Skip Previous",
                    modifier = Modifier.size(80.dp).clickable {
                        if(sliderPosition < 1f) {
                            vm.prevSong()
                        }
                        sliderPosition = 0f
                        vm.player.seekTo(0L)
                    }
                )

                var isPlaying by remember { mutableStateOf(false) }
                Image(
                    painter = painterResource(id = if(isPlaying) R.drawable.pause else R.drawable.play),
                    contentDescription = "Play Button",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            if (isPlaying) {
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
                    modifier = Modifier.size(80.dp).clickable {
                        vm.nextSong()
                        sliderPosition = 0f
                        vm.player.seekTo(0L)
                    }
                )

                Spacer(Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.repeat),
                    contentDescription = "Repeat",
                    colorFilter = ColorFilter.tint(repeatColor),
                    modifier = Modifier.size(30.dp).clickable {
                        if(vm.playableList.repeat) {
                            vm.playableList.repeat = false
                            repeatColor = Color.LightGray
                        } else {
                            vm.playableList.repeat = true
                            vm.playableList.shuffle = false
                            repeatColor = Color.Black
                            shuffleColor = Color.LightGray
                        }
                    }
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