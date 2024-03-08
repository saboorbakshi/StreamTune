package com.streamtune.streamtune.ui.playback

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.streamtune.streamtune.R
import com.streamtune.streamtune.ui.theme.StreamTuneTheme

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

            Text(text = "夜に駆ける", fontWeight = FontWeight.Bold, fontSize = 36.sp)

            Spacer(Modifier.height(10.dp))

            Text("YOASOBI", fontWeight = FontWeight.Medium, fontSize = 18.sp)


            Spacer(Modifier.height(25.dp))

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

                Image(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "Play Button",
                    modifier = Modifier.size(100.dp)
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
        PlaybackScreen(PlaybackViewModel())
    }
}