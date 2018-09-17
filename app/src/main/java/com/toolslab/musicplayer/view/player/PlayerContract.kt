package com.toolslab.musicplayer.view.player

import com.toolslab.musicplayer.base_mvp.BaseView
import com.toolslab.musicplayer.base_mvp.MvpPresenter

interface PlayerContract {

    interface Presenter : MvpPresenter<View> {
        fun onTrackTitleKnown(trackTitle: String)

        fun onPlayPauseClicked()
    }

    interface View : BaseView {
        fun setTitle(title: String)

        fun setPlayPauseButtonToPlaying()

        fun setPlayPauseButtonToPaused()
    }

}
