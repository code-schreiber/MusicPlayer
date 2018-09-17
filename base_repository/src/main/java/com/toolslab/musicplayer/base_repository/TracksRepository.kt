package com.toolslab.musicplayer.base_repository

import com.toolslab.musicplayer.base_network.model.Track
import com.toolslab.musicplayer.base_network.service.ApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TracksRepository @Inject constructor() {

    @Inject
    internal lateinit var apiService: ApiService

    @Inject
    internal lateinit var errorHandler: ErrorHandler

    private lateinit var cache: List<Track>

    fun listTracks(): Single<List<Track>> =
            if (::cache.isInitialized) {
                Single.just(cache)
            } else {
                getTopTracks()
            }

    private fun getTopTracks(): Single<List<Track>> =
            apiService.getTopTracks()
                    .onErrorResumeNext {
                        errorHandler.handle(it)
                    }
                    .map {
                        cache = it
                        it
                    }

}
