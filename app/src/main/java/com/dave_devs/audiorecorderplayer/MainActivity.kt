package com.dave_devs.audiorecorderplayer

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.dave_devs.audiorecorderplayer.ui.theme.AudioRecorderPlayerTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val recorder by lazy {
        AudioRecorderImpl(applicationContext)
    }
    private val player by lazy {
        AudioPlayerImpl(applicationContext)
    }
    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        setContent {
            AudioRecorderPlayerTheme {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        File(cacheDir, "audio.mp3").also {
                            recorder.startRecord(it)
                            audioFile = it
                        }
                    }) {
                        Text(text = "Start recording")
                    }
                    Button(onClick = {
                       recorder.pauseRecorde()
                    }) {
                        Text(text = "Pause recording")
                    }
                    Button(onClick = {
                        recorder.stop()
                    }) {
                        Text(text = "Stop recording")
                    }
                    Button(onClick = {
                       player.playAudio(audioFile ?: return@Button)
                    }) {
                        Text(text = "Play")
                    }
                    Button(onClick = {
                        player.pauseAudio()
                    }) {
                        Text(text = "Pause playing")
                    }
                    Button(onClick = {
                        player.stop()
                    }) {
                        Text(text = "Stop playing")
                    }
                }
            }
        }
    }
}