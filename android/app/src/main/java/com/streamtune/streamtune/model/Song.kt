package com.streamtune.streamtune.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "artist")
    val artist: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "album_cover")
    val albumCover: String,
    @ColumnInfo(name = "duration")
    val duration: Int,
)