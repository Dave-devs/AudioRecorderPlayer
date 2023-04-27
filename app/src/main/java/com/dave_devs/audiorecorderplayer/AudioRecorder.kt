package com.dave_devs.audiorecorderplayer

import java.io.File

interface AudioRecorder {

    fun startRecord(outputFile: File)
    fun pauseRecorde()
    fun stop()
}