package com.streamtune.streamtune.ui.greeting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.ui.theme.StreamTuneTheme


@Composable
fun Greeting(vm: GreetingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val title by vm.title.collectAsState()

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp,
                fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))

        var username by remember { mutableStateOf("") }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        val mainBtnLabel by vm.mainButtonLabel.collectAsState()
        Button(
            onClick = vm.onMainButtonClick,
            modifier = Modifier.width(200.dp)
        ) {
            Text(mainBtnLabel)
        }

        val auxBtnLabel by vm.auxButtonLabel.collectAsState()
        val interactionSource = remember { MutableInteractionSource() }
        Text(
            text = auxBtnLabel,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(15.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = vm.onAuxButtonClick
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StreamTuneTheme {
        Greeting(GreetingViewModel(rememberNavController()))
    }
}