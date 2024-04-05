package com.streamtune.streamtune.model

import kotlin.random.Random


// This is a model used for the frontend playback, and does not represent anything in the database/API


class PlayableList(val songs: List<Song>) {

    var playing = 0
    var shuffle: Boolean = false
    var repeat: Boolean = false
    var history: ArrayDeque<Int> = ArrayDeque()

    fun nextSong(): Song {

        if (repeat) {}
        else if(shuffle) {
            history.add(playing)
            playing = Random.nextInt(0, songs.size)
        }
        else {
            history.add(playing)
            playing += 1
            if(playing == songs.size) playing = 0
        }

        return songs[playing]

    }

    fun lastSong(): Song {

        if (repeat) {}
        else if(history.isNotEmpty()) {
            playing = history.removeLast()
        } else {
            if(shuffle) {
                playing = Random.nextInt(0, songs.size)
            } else {
                if(playing == 0) playing = songs.size
                playing -= 1
            }
        }

        return songs[playing]

    }

}