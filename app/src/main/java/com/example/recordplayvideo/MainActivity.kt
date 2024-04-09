package com.example.recordplayvideo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.recordplayvideo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val REQUEST_VIDEO_CAPTURE = 1
    private var videoUri: Uri? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        binding.buttonPlayVideo.setOnClickListener {
            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.ratm)
            binding.videoView.setVideoURI(uri)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.start()
        }

        binding.buttonPauseVideo.setOnClickListener {
            if (binding.videoView.isPlaying) {
                binding.videoView.pause()
            } else {
                binding.videoView.start()
            }
        }

        binding.buttonStopVideo.setOnClickListener {
            binding.videoView.stopPlayback()
            binding.videoView.setVideoURI(null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            videoUri = data?.data
        }
    }
}
