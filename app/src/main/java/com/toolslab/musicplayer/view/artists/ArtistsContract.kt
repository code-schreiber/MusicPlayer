package com.toolslab.musicplayer.view.artists

import com.toolslab.musicplayer.base_mvp.BaseView
import com.toolslab.musicplayer.base_mvp.MvpPresenter
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel

interface ArtistsContract {

    interface Presenter : MvpPresenter<View> {
        fun onArtistClicked(position: Int)
    }

    interface View : BaseView {
        fun setViewModels(viewModels: List<ArtistViewModel>)

        fun startArtistActivity(artistName: String)
    }

}
