package com.dave_devs.audiorecorderplayer

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

class AudioRecorderImpl(
    private val context: Context
): AudioRecorder {

    private var recorder: MediaRecorder? = null

    private fun createRecorder(): MediaRecorder {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()
    }

    override fun startRecord(outputFile: File) {
        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)

            recorder?.prepare()
            recorder?.start()

            recorder = this
        }
    }

    override fun pauseRecorde() {
        recorder?.pause()
        recorder?.resume()
    }

    override fun stop() {
       recorder?.stop()
        recorder?.reset()
        recorder = null
    }
}