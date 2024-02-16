package com.streamtune.streamtune

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SongList() {

    Surface(Modifier.fillMaxSize()) {

        Column(Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp).fillMaxHeight(), verticalArrangement = Arrangement.Center) {

            Text(text = "Find Your", color = Color.Gray, fontSize = 28.sp)
            Text(text = "Music", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 28.sp)

            Spacer(Modifier.height(50.dp))

            OutlinedTextField(
                value = "",
                label = { Text("Link or Search YouTube") },
                leadingIcon = { Image(painter = painterResource(id = R.drawable.search), contentDescription = "Search") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {}
            )

            Button(content = { Text("FIND") }, modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 15.dp, 0.dp, 0.dp),
                onClick = {}
            )

            Spacer(Modifier.height(100.dp))

        }

    }

}