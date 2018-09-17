package com.toolslab.musicplayer.view.player

import com.toolslab.musicplayer.background.BackgroundMusicPlayer
import com.toolslab.musicplayer.base_mvp.BasePresenter
import com.toolslab.musicplayer.base_repository.exception.NoConnectionException
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PlayerPresenter @Inject constructor()
    : BasePresenter<PlayerContract.View>(), PlayerContract.Presenter {

    @Inject
    internal lateinit var compositeDisposable: CompositeDisposable

    @Inject
    internal lateinit var playerInteractor: PlayerInteractor

    @Inject
    internal lateinit var backgroundMusicPlayer: BackgroundMusicPlayer

    private lateinit var track: TrackViewModel

    private var playing = false

    override fun onUnbound(view: PlayerContract.View) {
        compositeDisposable.clear()
    }

    override fun onTrackTitleKnown(trackTitle: String) {
        compositeDisposable.add(playerInteractor.getTrack(trackTitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("Track ${it.title} found")
                            track = it
                            view.setTitle(track.title)
                            playTrack()
                        },
                        {
                            Timber.e(it)
                            when (it) {
                                is NoConnectionException -> view.showNoConnectionError()
                                else -> view.showDefaultError()
                            }
                        }
                )
        )
    }

    override fun onPlayPauseClicked() {
        if (playing) {
            pauseTrack()
        } else {
            playTrack()
        }
    }

    private fun pauseTrack() {
        backgroundMusicPlayer.pauseTrack()
        view.setPlayPauseButtonToPaused()
        playing = false
    }

    private fun playTrack() {
        backgroundMusicPlayer.playTrack(track)
        view.setPlayPauseButtonToPlaying()
        playing = true
    }

}
