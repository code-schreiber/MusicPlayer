package com.toolslab.musicplayer.base_mvp

interface BaseView : MvpView {
    fun showNoConnectionError()

    fun showDefaultError()
}
