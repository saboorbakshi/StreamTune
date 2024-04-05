package com.streamtune.streamtune

import com.streamtune.streamtune.ui.addsong.AddSongViewModel
import com.streamtune.streamtune.ui.playback.PlaybackViewModel
import com.streamtune.streamtune.ui.songlist.SongListViewModel

class VMStore {

    lateinit var playbackVM: PlaybackViewModel
    lateinit var songListVM: SongListViewModel
    lateinit var addSongVM: AddSongViewModel

}