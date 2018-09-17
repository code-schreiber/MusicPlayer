package com.toolslab.musicplayer.view.artist

import com.toolslab.musicplayer.base_mvp.BaseView
import com.toolslab.musicplayer.base_mvp.MvpPresenter
import com.toolslab.musicplayer.base_repository.model.TrackViewModel

interface ArtistContract {

    interface Presenter : MvpPresenter<View> {
        fun onArtistNameKnown(artistName: String)

        fun onSongClicked(position: Int)
    }

    interface View : BaseView {
        fun setArtistName(artistName: String)

        fun setViewModels(viewModels: List<TrackViewModel>)

        fun startPlayerActivity(trackTitle: String)
    }

}
