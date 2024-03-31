package com.streamtune.streamtune.network

import android.util.Log
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.streamtune.streamtune.model.Playlist
import com.streamtune.streamtune.model.Song
import kotlinx.coroutines.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object ApiCalls {
    fun addSong(youtubeURL: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.ADD_SONG_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Authorization", ApiConfig.authToken)
                connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                readTimeout = ApiConfig.READ_TIMEOUT
                doOutput = true

                val requestBody = "{\"url\": \"$youtubeURL\"}"

                try {
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        ApiConfig.songAdded = true
                        ApiConfig.toast = "Your song was successfully added."
                        Log.i("ADD SONG SUCCESS", "Request successful with response code $responseCode")
                    } else {
                        ApiConfig.songAdded = false
                        ApiConfig.toast = "Your song could not be added."
                        Log.e("ADD SONG ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    ApiConfig.toast = "We're unable to add songs right now. Please try again later."
                    Log.e("ADD SONG ERROR", "Error during addSong API call: ${e.message}: $responseCode")
                } finally {
                    disconnect()
                }
            }
        }
    }

    fun addToPlaylist(playlistName: String, songID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.ADD_TO_PLAYLIST_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Authorization", ApiConfig.authToken)
                connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                readTimeout = ApiConfig.READ_TIMEOUT
                doOutput = true

                val requestBody = "{\"playlist_name\": \"$playlistName\", \"song_id\": \"$songID\"}"

                try {
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.i("ADD2PLAYLIST SUCCESS", "Request successful with response code $responseCode")
                    } else {
                        ApiConfig.toast = "Your song could not be added to $playlistName."
                        Log.e("ADD2PLAYLIST ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    ApiConfig.toast = "We're unable to add songs to playlists right now. Please try again later."
                    Log.e("ADD2PLAYLIST ERROR", "Error during createPlaylist API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }

    fun createPlaylist(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.CREATE_PLAYLIST_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Authorization", ApiConfig.authToken)
                connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                readTimeout = ApiConfig.READ_TIMEOUT
                doOutput = true

                val requestBody = "{\"name\": \"$name\"}"

                try {
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.i("CREATE PLAYLIST SUCCESS", "Request successful with response code $responseCode")
                    } else {
                        ApiConfig.toast = "Your playlist could not be created."
                        Log.e("CREATE PLAYLIST ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    ApiConfig.toast = "We're unable to create playlists right now. Please try again later."
                    Log.e("CREATE PLAYLIST ERROR", "Error during createPlaylist API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }

    fun getPlaylists() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.GET_PLAYLISTS_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Authorization", ApiConfig.authToken)
                connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                readTimeout = ApiConfig.READ_TIMEOUT

                try {
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        val response = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
                        Log.i("GET PLAYLISTS SUCCESS", "Request successful with response code $responseCode")

                        // parse JSON received
                        val gson = Gson()
                        val playlistsType = object : TypeToken<List<Playlist>>() {}.type
                        ApiConfig.playlists = gson.fromJson(response, playlistsType)

                        // ApiConfig.playlists.forEach { playlist -> println(playlist) }

                    } else {
                        ApiConfig.toast = "Your playlists could not be updated."
                        Log.e("GET PLAYLISTS ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    ApiConfig.toast = "We're unable to update your playlists right now. Please try again later."
                    Log.e("GET PLAYLISTS ERROR", "Error during createPlaylist API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }
}

