package com.streamtune.streamtune.network

object ApiConfig {
    var authToken = ""

    // Base URL
    const val BASE_URL = "https://streamtune.azurewebsites.net/"

    // Endpoint constants
    const val ADD_SONG_ENDPOINT = "convert"
    const val ADD_TO_PLAYLIST_ENDPOINT = "addToPlaylist"
    const val CREATE_PLAYLIST_ENDPOINT = "createPlaylist"
    const val GET_PLAYLISTS_ENDPOINT = "getPlaylists"

    // Timeout settings
    const val CONNECTION_TIMEOUT = 10000
    const val WRITE_TIMEOUT = 10000
    const val READ_TIMEOUT = 10000

}