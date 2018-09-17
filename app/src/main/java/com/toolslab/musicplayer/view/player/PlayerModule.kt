package com.toolslab.musicplayer.view.player

import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @Provides
    fun providePresenter(presenter: PlayerPresenter): PlayerContract.Presenter = presenter

}
