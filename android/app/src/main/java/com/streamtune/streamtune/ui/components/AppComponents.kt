package com.streamtune.streamtune.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.streamtune.streamtune.R
import com.streamtune.streamtune.network.ApiConfig

@Composable
fun HeadingTextComponent(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp,
            fontWeight = FontWeight.Bold)
    )
}

@Composable
fun NormalTextFieldComponent(label: String, onValueChange: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    OutlinedTextField(
        value = email,
        onValueChange = {
            email = it
            onValueChange(it)
        },
        label = { Text(label) }
    )
}

@Composable
fun PasswordTextFieldComponent(label: String, onValueChange: (String) -> Unit) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
            onValueChange(it)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done),
        trailingIcon = {
            val iconImage = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = iconImage, contentDescription = "Password Visibility")
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ButtonComponent(label: String, onClick: () -> Unit) {
    val context = LocalContext.current
    Button(
        onClick = {
            onClick()
            val text = ApiConfig.toast
            if (text.isNotEmpty()) {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                ApiConfig.toast = ""
            }
        },
        modifier = Modifier.width(225.dp).height(45.dp)
    ) {
        Text(label)
    }
}

@Composable
fun ClickableTextComponent(msg: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Text(
        text = msg,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .padding(15.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    )
}

@Composable
fun SearchTextFieldComponent(label: String, onValueChange: (String) -> Unit) {
    var url by remember { mutableStateOf("") }
    OutlinedTextField(
        value = url,
        onValueChange = {
            url = it
            onValueChange(it)
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = { Image(painter = painterResource(id = R.drawable.search), contentDescription = "Search") }
    )
}
