package com.dave_devs.audiorecorderplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AudioPlayerImpl(
    private val context: Context
): AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playAudio(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            player?.start()
        }
    }

    override fun pauseAudio() {
        player?.pause()
        player?.stop()
    }

    override fun stop() {
        player?.stop()
        player = null
    }
}