package com.toolslab.musicplayer.view.artist

import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @Provides
    fun providePresenter(presenter: ArtistPresenter): ArtistContract.Presenter = presenter

}
