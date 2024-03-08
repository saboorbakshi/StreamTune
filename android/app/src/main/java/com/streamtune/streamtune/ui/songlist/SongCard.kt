package com.streamtune.streamtune.ui.songlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.streamtune.streamtune.R

@Preview
@Composable
fun SongCard() {

    Surface {

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = R.drawable.yoasobi_the_book),
                contentDescription = "AlbumArt",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(verticalArrangement = Arrangement.Center) {

                Text(text = "夜に駆ける", fontWeight = FontWeight.SemiBold, fontSize = 24.sp)

                Spacer(Modifier.height(5.dp))

                Text("YOASOBI", fontWeight = FontWeight.Medium, fontSize = 18.sp)

            }

            Spacer(Modifier.weight(1f))

            Image(painter = painterResource(id = R.drawable.more), contentDescription = "more",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
                modifier = Modifier.size(50.dp).padding(15.dp)
            )

        }

    }

}