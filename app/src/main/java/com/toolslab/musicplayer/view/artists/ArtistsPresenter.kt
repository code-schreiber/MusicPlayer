package com.toolslab.musicplayer.view.artists

import android.support.annotation.VisibleForTesting
import com.toolslab.musicplayer.background.BackgroundMusicPlayer
import com.toolslab.musicplayer.base_mvp.BasePresenter
import com.toolslab.musicplayer.base_repository.exception.NoConnectionException
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ArtistsPresenter @Inject constructor()
    : BasePresenter<ArtistsContract.View>(), ArtistsContract.Presenter {

    @Inject
    internal lateinit var compositeDisposable: CompositeDisposable

    @Inject
    internal lateinit var artistsInteractor: ArtistsInteractor

    @Inject
    internal lateinit var backgroundMusicPlayer: BackgroundMusicPlayer

    @VisibleForTesting
    internal lateinit var artists: List<ArtistViewModel>

    override fun onBound(view: ArtistsContract.View) {
        compositeDisposable.add(artistsInteractor.listArtists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("${it.size} artists found")
                            artists = it
                            view.setViewModels(artists)
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

    override fun onUnbound(view: ArtistsContract.View) {
        compositeDisposable.clear()
        backgroundMusicPlayer.releaseTrack()
    }

    override fun onArtistClicked(position: Int) {
        view.startArtistActivity(artists[position].name)
    }

}
