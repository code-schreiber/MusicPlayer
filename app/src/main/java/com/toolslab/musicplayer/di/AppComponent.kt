package com.toolslab.musicplayer.di

import android.app.Application
import com.toolslab.musicplayer.MusicPlayer
import com.toolslab.musicplayer.base_repository.di.RepositoryComponent
import com.toolslab.musicplayer.view.artist.ArtistModule
import com.toolslab.musicplayer.view.artists.ArtistsModule
import com.toolslab.musicplayer.view.player.PlayerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivitiesBindingModule::class,
            ArtistsModule::class,
            ArtistModule::class,
            PlayerModule::class
        ],
        dependencies = [
            RepositoryComponent::class
        ]
)
interface AppComponent : AndroidInjector<MusicPlayer> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun repositoryComponent(repositoryComponent: RepositoryComponent): Builder

        fun build(): AppComponent
    }
}
