package com.toolslab.musicplayer

import com.facebook.drawee.backends.pipeline.Fresco
import com.toolslab.musicplayer.di.DaggerAppComponent
import com.toolslab.musicplayer.base_repository.di.DaggerRepositoryComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class MusicPlayer : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val repositoryComponent = DaggerRepositoryComponent.builder().build()
        return DaggerAppComponent
                .builder()
                .create(this)
                .repositoryComponent(repositoryComponent)
                .build()
    }

}
