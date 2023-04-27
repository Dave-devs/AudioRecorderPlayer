package com.dave_devs.audiorecorderplayer

import java.io.File

interface AudioPlayer {

    fun playAudio(file: File)
    fun pauseAudio()
    fun stop()
}