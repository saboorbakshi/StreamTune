package com.streamtune.streamtune.network

import com.streamtune.streamtune.model.Playlist

object ApiConfig {
    var authToken : String? = null

    // Base URL
    const val BASE_URL = "https://streamtune.azurewebsites.net/"

    // Endpoint constants
    const val ADD_SONG_ENDPOINT = "convert"
    const val ADD_TO_PLAYLIST_ENDPOINT = "addToPlaylist"
    const val CREATE_PLAYLIST_ENDPOINT = "createPlaylist"
    const val GET_PLAYLISTS_ENDPOINT = "getPlaylists"
    const val DELETE_FROM_PLAYLIST_ENDPOINT = "deleteFromPlaylist"
    const val DELETE_PLAYLIST_ENDPOINT = "deletePlaylist"

    // Timeout settings
    const val CONNECTION_TIMEOUT = 10000
    const val READ_TIMEOUT = 10000
}