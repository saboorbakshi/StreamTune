package com.streamtune.streamtune.network

import android.util.Log
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.model.Playlist
import kotlinx.coroutines.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object ApiCalls {
    suspend fun addSong(youtubeURL: String): String? {
        return withContext(Dispatchers.IO) {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.ADD_SONG_ENDPOINT)
            var songID: String? = null
            (url.openConnection() as HttpURLConnection).apply {
                try {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Authorization", ApiConfig.authToken)
                    connectTimeout = ApiConfig.CONNECTION_TIMEOUT * 2
                    readTimeout = ApiConfig.READ_TIMEOUT * 2
                    doOutput = true

                    val requestBody = "{\"url\": \"$youtubeURL\"}"
                    OutputStreamWriter(outputStream).use { it.write(requestBody) }

                    // Construct and log the cURL command
                    val curlCommand = """
                    curl -X POST \
                    '${url}' \
                    -H 'Content-Type: application/json' \
                    -H 'Authorization: ${getRequestProperty("Authorization")}' \
                    -d '$requestBody'
                    """.trimIndent()
                    Log.i("CURL COMMAND", curlCommand)

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        songID = inputStream.bufferedReader().use(BufferedReader::readText)
                        Log.i("ADD SONG SUCCESS", "Request successful with response code $responseCode, songID: $songID.")
                    } else {
                        Log.e("ADD SONG ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    Log.e("ADD SONG ERROR", "Error during addSong API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
            songID
        }
    }

    suspend fun addToPlaylist(playlistName: String, songID: String) {
        return withContext(Dispatchers.IO) {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.ADD_TO_PLAYLIST_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Authorization", ApiConfig.authToken)
                connectTimeout = 10000;
                readTimeout = 10000;
                doOutput = true

                val requestBody = "{\"playlist_name\": \"$playlistName\", \"song_id\": \"$songID\"}"

                try {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Authorization", ApiConfig.authToken)
                    connectTimeout = 10000
                    readTimeout = 10000
                    doOutput = true

                    val requestBody = "{\"playlist_name\": \"$playlistName\", \"song_id\": \"$songID\"}"
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.i("ADD2PLAYLIST SUCCESS", "Request successful with response code $responseCode, Added to: $playlistName.")
                    } else {
                        Log.e("ADD2PLAYLIST ERROR", "Request failed with response code $responseCode, Added to: $playlistName.")
                    }
                } catch (e: Exception) {
                    Log.e("ADD2PLAYLIST ERROR", "Error during addToPlaylist API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }

    suspend fun createPlaylist(name: String) {
        return withContext(Dispatchers.IO) {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.CREATE_PLAYLIST_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                try {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Authorization", ApiConfig.authToken)
                    connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                    readTimeout = ApiConfig.READ_TIMEOUT
                    doOutput = true

                    val requestBody = "{\"name\": \"$name\"}"
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        ApiConfig.playlistCreated = true
                        ApiConfig.toast = "$name was successfully created."
                        Log.i("CREATE PLAYLIST SUCCESS", "Request successful with response code $responseCode")
                    } else {
                        Log.e("CREATE PLAYLIST ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    Log.e("CREATE PLAYLIST ERROR", "Error during createPlaylist API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }

    suspend fun getPlaylists() {
        return withContext(Dispatchers.IO) {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.GET_PLAYLISTS_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                try {
                    requestMethod = "GET"
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Authorization", ApiConfig.authToken)
                    connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                    readTimeout = ApiConfig.READ_TIMEOUT

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        val response = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
                        Log.i("GET PLAYLISTS SUCCESS", "Request successful with response code $responseCode")

                        // parse JSON received
                        val gson = Gson()
                        val playlistsType = object : TypeToken<List<Playlist>>() {}.type
                        StreamTune.allPlaylists = gson.fromJson(response, playlistsType)
                    } else {
                        Log.e("GET PLAYLISTS ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    Log.e("GET PLAYLISTS ERROR", "Error during getPlaylists API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }

    suspend fun deletePlaylist(name: String) {
        return withContext(Dispatchers.IO) {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.DELETE_PLAYLIST_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                try {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Authorization", ApiConfig.authToken)
                    connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                    readTimeout = ApiConfig.READ_TIMEOUT
                    doOutput = true

                    val requestBody = "{\"name\": \"$name\"}"
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.i("DELETE PLAYLIST SUCCESS", "Request successful with response code $responseCode")
                    } else {
                        Log.e("DELETE PLAYLIST ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    Log.e("DELETE PLAYLIST ERROR", "Error during deletePlaylist API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }

    suspend fun deleteFromPlaylist(playlistName: String, songID: String) {
        return withContext(Dispatchers.IO) {
            val url = URL(ApiConfig.BASE_URL + ApiConfig.DELETE_FROM_PLAYLIST_ENDPOINT)
            (url.openConnection() as HttpURLConnection).apply {
                try {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Authorization", ApiConfig.authToken)
                    connectTimeout = ApiConfig.CONNECTION_TIMEOUT
                    readTimeout = ApiConfig.READ_TIMEOUT
                    doOutput = true

                    val requestBody = "{\"playlist_name\": \"$playlistName\", \"song_id\": \"$songID\"}"
                    OutputStreamWriter(outputStream).apply {
                        write(requestBody)
                        flush()
                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.i("DELETE FROM PLAYLIST SUCCESS", "Request successful with response code $responseCode")
                    } else {
                        Log.e("DELETE FROM PLAYLIST ERROR", "Request failed with response code $responseCode")
                    }
                } catch (e: Exception) {
                    Log.e("DELETE FROM PLAYLIST ERROR", "Error during deleteFromPlaylist API call: ${e.message}")
                } finally {
                    disconnect()
                }
            }
        }
    }
}

