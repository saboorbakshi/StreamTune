package com.streamtune.streamtune.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Song(
    @PrimaryKey
    val id: String,
    @SerializedName("name")
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "artist")
    val artist: String,
    @ColumnInfo(name = "url")
    val url: String,
    @SerializedName("album_cover")
    @ColumnInfo(name = "album_cover")
    val albumCover: String,
    @ColumnInfo(name = "duration")
    val duration: Int
) {
    override fun toString(): String {
        return "Song(id=$id, title=\"$title\", artist=\"$artist\", url=$url, albumCover=$albumCover, duration=$duration)"
    }
}
