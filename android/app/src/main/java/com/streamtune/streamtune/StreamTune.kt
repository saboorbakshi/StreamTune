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

        var allSongs: MutableList<Song> = mutableListOf(
            Song("69", "夜に駆ける", "YOASOBI", "https://franktao.com/files/yorunikakeru.mp3", "helloCover", 120),
            Song("70", "アイドル", "YOASOBI", "https://franktao.com/files/idol.mp3", "blah", 120)
        )

        var allPlaylists: MutableList<Playlist> = mutableListOf()

        val VMStore: VMStore = VMStore()

    }

}