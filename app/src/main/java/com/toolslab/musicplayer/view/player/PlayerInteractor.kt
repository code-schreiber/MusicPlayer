package com.toolslab.musicplayer.view.player

import android.support.annotation.CheckResult
import com.toolslab.musicplayer.base_repository.TracksRepository
import com.toolslab.musicplayer.base_repository.converter.TrackViewModelConverter
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import io.reactivex.Single
import javax.inject.Inject

class PlayerInteractor @Inject constructor() {

    @Inject
    internal lateinit var tracksRepository: TracksRepository

    @Inject
    internal lateinit var trackViewModelConverter: TrackViewModelConverter

    @CheckResult
    internal fun getTrack(trackTitle: String): Single<TrackViewModel> =
            tracksRepository.listTracks()
                    .map { tracks ->
                        tracks.filter {
                            it.title == trackTitle
                        }.map {
                            trackViewModelConverter.convert(it)
                        }.single()
                    }

}
