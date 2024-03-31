package com.streamtune.streamtune.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamtune.streamtune.network.ApiConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class AppViewModel : ViewModel() {
    fun launchCatching(block: suspend CoroutineScope.() -> Unit, errorTag: String = "APP ERROR") =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                ApiConfig.toast = throwable.message.orEmpty()
                Log.e(errorTag, throwable.message.orEmpty())
            },
            block = block
        )
}