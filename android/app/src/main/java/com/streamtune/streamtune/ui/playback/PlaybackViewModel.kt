package com.streamtune.streamtune.ui.playback

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.model.PlayableList
import com.streamtune.streamtune.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.max

@SuppressLint("StaticFieldLeak")
class PlaybackViewModel(song: Song): ViewModel() {

    private val context = StreamTune.applicationContext()
    val player: ExoPlayer = ExoPlayer.Builder(context).build()

    val nowPlaying: MutableStateFlow<Song> = MutableStateFlow(song)
    var songDuration: MutableStateFlow<Float> = MutableStateFlow(max(song.duration.toFloat(), 0f))

    val playableList: PlayableList = PlayableList(StreamTune.allSongs)

    init {

        playableList.history.add(playableList.songs.indexOf(song))
        playSong(song)

    }

    private fun playSong(song: Song) {
        player.clearMediaItems()

        val mediaItem = MediaItem.fromUri(song.url)
        player.addMediaItem(mediaItem)
        player.prepare()

        songDuration.value = max(song.duration.toFloat(), 0f)
    }

    fun nextSong() {
        nowPlaying.value = playableList.nextSong()
        playSong(nowPlaying.value)
    }

    fun prevSong() {
        nowPlaying.value = playableList.lastSong()
        playSong(nowPlaying.value)
    }

    fun secondsToTimestamp(seconds: Float): String {

        var remaining = seconds
        var retval = ""

        // Get Hours
        if(remaining >= 3600) retval += "%02d".format((remaining / 3600).toInt()) + ":"
        remaining %= 3600

        // Get Minutes
        retval += "%02d".format((remaining / 60).toInt()) + ":"
        remaining %= 60

        // Get Seconds
        retval += "%02d".format(remaining.toInt())

        return retval

    }

}