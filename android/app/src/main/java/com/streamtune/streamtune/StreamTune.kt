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
            Song("69", "夜に駆ける", "YOASOBI", "https://franktao.com/files/yorunikakeru.mp3", "https://upload.wikimedia.org/wikipedia/en/9/93/Yoru_ni_Kakeru_cover_art.jpg", 120),
            Song("70", "アイドル", "YOASOBI", "https://franktao.com/files/idol.mp3", "https://upload.wikimedia.org/wikipedia/en/b/b0/Yoasobi_-_Idol.png", 160)
        )

        var allPlaylists: MutableList<Playlist> = mutableListOf(
            Playlist("Some Songs",
                mutableListOf(
                    Song("69", "夜に駆ける", "YOASOBI", "https://franktao.com/files/yorunikakeru.mp3", "https://upload.wikimedia.org/wikipedia/en/9/93/Yoru_ni_Kakeru_cover_art.jpg", 120),
                    Song("70", "アイドル", "YOASOBI", "https://franktao.com/files/idol.mp3", "https://upload.wikimedia.org/wikipedia/en/b/b0/Yoasobi_-_Idol.png", 160)
                )
            ),
            Playlist("Less Songs", emptyList())
        )

        val VMStore: VMStore = VMStore()

    }

}