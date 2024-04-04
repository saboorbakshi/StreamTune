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

        var playlists = listOf<Playlist>()

        private var instance: StreamTune? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        val allSongs: MutableList<Song> = mutableListOf(
            Song("69", "夜に駆ける", "YOASOBI", "https://franktao.com/files/yorunikakeru.mp3", "", 1),
            Song("70", "アイドル", "YOASOBI", "https://franktao.com/files/idol.mp3", "", 1)
        )

        val VMStore: VMStore = VMStore()

    }

}