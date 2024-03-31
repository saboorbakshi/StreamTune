package com.streamtune.streamtune.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "songs")
    val songs: List<Song>
) {
    override fun toString(): String {
        val songsString = songs.joinToString(separator = "\n\t", prefix = "\n\t")
        return "Playlist(name=$name, songs=$songsString)"
    }
}
