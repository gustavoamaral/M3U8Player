package com.exemplo.m3u8player

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import com.exemplo.m3u8player.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player: ExoPlayer? = null

    private val pickFileLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            try {
                contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: SecurityException) {
                // Ignora se não conseguir permissão persistente
            }
            initializePlayer(it)
            binding.editUrl.setText(it.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlayUrl.setOnClickListener {
            val url = binding.editUrl.text.toString().trim()
            if (url.isNotEmpty()) {
                initializePlayer(Uri.parse(url))
            } else {
                Toast.makeText(this, "Digite um link m3u8 válido", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnOpenFile.setOnClickListener {
            pickFileLauncher.launch(arrayOf("*/*"))
        }
    }

    private fun initializePlayer(uri: Uri) {
        releasePlayer()

        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val mediaItem = MediaItem.Builder()
            .setUri(uri)
            .setMimeType(MimeTypes.APPLICATION_M3U8)
            .build()

        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.playWhenReady = true
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}