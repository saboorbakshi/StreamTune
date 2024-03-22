package com.streamtune.streamtune.ui.addsong

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.R
import com.streamtune.streamtune.ui.components.ButtonComponent
import com.streamtune.streamtune.ui.components.SearchTextFieldComponent
import com.streamtune.streamtune.ui.theme.StreamTuneTheme

@Composable
fun AddSong(vm: AddSongViewModel) {

    Surface(Modifier.fillMaxSize()) {

        Column(
            Modifier
                .padding(40.dp, 0.dp, 40.dp, 0.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = stringResource(id = R.string.find_music), color = Color.Gray, fontSize = 24.sp)

            Spacer(Modifier.height(20.dp))

            SearchTextFieldComponent(label = stringResource(id = R.string.search_youtube_msg), onValueChange = { vm.link = it })

            Spacer(Modifier.height(20.dp))

            ButtonComponent(label = stringResource(id = R.string.add_song), onClick = { vm.onAddButtonClick() })

            Spacer(Modifier.height(100.dp))

        }

    }

}

@Preview
@Composable
private fun AddSongPreview() {
    StreamTuneTheme {
        AddSong(AddSongViewModel(rememberNavController()))
    }
}