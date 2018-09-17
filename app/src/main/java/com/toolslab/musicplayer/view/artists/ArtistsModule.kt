package com.toolslab.musicplayer.view.artists

import dagger.Module
import dagger.Provides

@Module
class ArtistsModule {

    @Provides
    fun providePresenter(presenter: ArtistsPresenter): ArtistsContract.Presenter = presenter

}
