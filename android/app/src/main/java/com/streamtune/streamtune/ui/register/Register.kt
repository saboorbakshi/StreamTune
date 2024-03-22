package com.streamtune.streamtune.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.streamtune.streamtune.R
import com.streamtune.streamtune.ui.components.ButtonComponent
import com.streamtune.streamtune.ui.components.ClickableTextComponent
import com.streamtune.streamtune.ui.components.HeadingTextComponent
import com.streamtune.streamtune.ui.components.NormalTextFieldComponent
import com.streamtune.streamtune.ui.components.PasswordTextFieldComponent
import com.streamtune.streamtune.ui.theme.StreamTuneTheme

@Composable
fun Register(vm: RegisterViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        HeadingTextComponent(label = "REGISTER")

        Spacer(modifier = Modifier.height(20.dp))

        NormalTextFieldComponent(label = stringResource(id = R.string.email), onValueChange = { vm.email = it })

        PasswordTextFieldComponent(label = stringResource(id = R.string.password), onValueChange = { vm.password = it })

        PasswordTextFieldComponent(label = stringResource(id = R.string.confirm_password), onValueChange = { vm.confirmPassword = it })

        Spacer(modifier = Modifier.height(20.dp))

        ButtonComponent(label = stringResource(id = R.string.register), onClick = { vm.onRegButtonClick() })

        ClickableTextComponent(msg = stringResource(id = R.string.register_msg), onClick =  { vm.onAuxButtonClick() })
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterPreview() {
    StreamTuneTheme {
        Register(RegisterViewModel(rememberNavController()))
    }
}