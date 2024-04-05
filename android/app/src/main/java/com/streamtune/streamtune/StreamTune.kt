package com.streamtune.streamtune

import android.app.Application
import android.content.Context
import com.streamtune.streamtune.model.Playlist
import com.streamtune.streamtune.model.Song

class StreamTune: Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: StreamTune? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        var allSongs: MutableList<Song> = mutableListOf()

        var allPlaylists: MutableList<Playlist> = mutableListOf()

        val VMStore: VMStore = VMStore()

    }

}