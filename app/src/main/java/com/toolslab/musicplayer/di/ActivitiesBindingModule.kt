package com.toolslab.musicplayer.di

import com.toolslab.musicplayer.view.artist.ArtistActivity
import com.toolslab.musicplayer.view.artists.ArtistsActivity
import com.toolslab.musicplayer.view.player.PlayerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector
    abstract fun artistsActivity(): ArtistsActivity

    @ContributesAndroidInjector
    abstract fun artistActivity(): ArtistActivity

    @ContributesAndroidInjector
    abstract fun playerActivity(): PlayerActivity

}
