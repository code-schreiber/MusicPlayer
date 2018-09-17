package com.toolslab.musicplayer.base_repository.di

import com.toolslab.musicplayer.base_network.di.NetworkModule
import com.toolslab.musicplayer.base_network.service.ApiService
import dagger.Component

@Component(
        modules = [NetworkModule::class]
)
interface RepositoryComponent {

    // Provision method so ApiService gets provided to consumers of this component
    fun provideApiService(): ApiService

    @Component.Builder
    interface Builder {
        fun build(): RepositoryComponent
    }

}
