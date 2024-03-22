package com.streamtune.streamtune.ui.playback

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.streamtune.streamtune.StreamTune
import com.streamtune.streamtune.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.max

@SuppressLint("StaticFieldLeak")
class PlaybackViewModel(val song: Song): ViewModel() {

    private val context = StreamTune.applicationContext()
    val player: ExoPlayer = ExoPlayer.Builder(context).build()

    val songDuration: MutableStateFlow<Float> = MutableStateFlow(max(player.duration.toFloat(), 240f))

    init {

        val mediaItem = MediaItem.fromUri(song.url)
        player.addMediaItem(mediaItem)
        player.prepare()

        // songDuration.value = max(player.duration.toFloat(), 240f)

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