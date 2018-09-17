package com.toolslab.musicplayer.view.artist

import com.toolslab.musicplayer.base_mvp.BasePresenter
import com.toolslab.musicplayer.base_repository.exception.NoConnectionException
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ArtistPresenter @Inject constructor()
    : BasePresenter<ArtistContract.View>(), ArtistContract.Presenter {

    @Inject
    internal lateinit var compositeDisposable: CompositeDisposable

    @Inject
    internal lateinit var artistInteractor: ArtistInteractor

    private lateinit var tracks: List<TrackViewModel>

    override fun onUnbound(view: ArtistContract.View) {
        compositeDisposable.clear()
    }

    override fun onArtistNameKnown(artistName: String) {
        compositeDisposable.add(artistInteractor.listTracks(artistName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("${it.size} tracks found for $artistName")
                            tracks = it
                            view.setArtistName(artistName)
                            view.setViewModels(tracks)
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

    override fun onSongClicked(position: Int) {
        view.startPlayerActivity(tracks[position].title)
    }

}
