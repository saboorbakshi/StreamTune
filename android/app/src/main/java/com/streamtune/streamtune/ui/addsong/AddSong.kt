package com.streamtune.streamtune.ui.addsong

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.R
import com.streamtune.streamtune.ui.components.ButtonComponent
import com.streamtune.streamtune.ui.components.IconTextFieldComponent
import com.streamtune.streamtune.ui.theme.StreamTuneTheme
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun AddSong(vm: AddSongViewModel) {

    val context = LocalContext.current
    vm.showToast = { message ->
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    val subMsg = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray, fontSize = 22.sp)) {
            append("Add to ")
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 22.sp)) {
            append(vm.playlistName)
        }
    }

    val isLoading by vm.isLoading.observeAsState(false)

    Surface(Modifier.fillMaxSize()) {

        if (isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator() // Show loading spinner
            }

        } else {

            Column(
                Modifier
                    .padding(40.dp, 0.dp, 40.dp, 0.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = stringResource(id = R.string.find_music), color = Color.Gray, fontSize = 36.sp)

                Spacer(Modifier.height(15.dp))

                Text(
                    text = subMsg,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(30.dp))

                IconTextFieldComponent(
                    label = stringResource(id = R.string.search_youtube_msg),
                    onValueChange = { vm.link = it },
                    R.drawable.music
                )

                Spacer(Modifier.height(30.dp))

                ButtonComponent(label = stringResource(id = R.string.add_song), onClick = { vm.onAddButtonClick() })

                Spacer(Modifier.height(100.dp))
            }
        }
    }

}

@Preview
@Composable
private fun AddSongPreview() {
    StreamTuneTheme {
        AddSong(AddSongViewModel(rememberNavController(), "Preview Playlist"))
    }
}