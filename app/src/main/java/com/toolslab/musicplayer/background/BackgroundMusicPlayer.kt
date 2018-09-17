package com.toolslab.musicplayer.background

import android.media.MediaPlayer
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackgroundMusicPlayer @Inject constructor() {

    private var mediaPlayer: MediaPlayer? = null

    fun playTrack(viewModel: TrackViewModel) {
        try {
            stopTrack()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(viewModel.streamUrl)
                prepare()
                start()
            }
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
        } catch (e: IOException) {
            Timber.e(e)
        }
    }

    fun pauseTrack() {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
        }
    }

    fun releaseTrack() {
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    private fun stopTrack() {
        if (mediaPlayer != null) {
            val isPlaying = mediaPlayer?.isPlaying
            if (isPlaying != null && isPlaying) {
                mediaPlayer?.stop()
            }
        }
    }

}
