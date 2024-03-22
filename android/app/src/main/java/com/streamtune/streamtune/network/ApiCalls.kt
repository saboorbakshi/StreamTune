package com.streamtune.streamtune.network

import android.util.Log
import kotlinx.coroutines.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object ApiCalls {
    fun postSongRequest(youtubeURL: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.ADD_SONG_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                setRequestProperty("Authorization", ApiConfig.authToken)
                connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                readTimeout = ApiConfig.READ_TIMEOUT
                doOutput = true

                // Request body
                val requestBody = "{\"url\": \"$youtubeURL\"}"

                try {
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.i("POST SONG", "Request successful with response code $responseCode")
                    } else {
                        Log.e("POST SONG ERROR", "Request failed with response code $responseCode")
                        println()
                    }
                } catch (e: IOException) {
                    Log.e("POST SONG ERROR", "Error during postSongRequest API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }
}
