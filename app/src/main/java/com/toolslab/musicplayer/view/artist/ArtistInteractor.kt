package com.toolslab.musicplayer.view.artist

import android.support.annotation.CheckResult
import com.toolslab.musicplayer.base_repository.TracksRepository
import com.toolslab.musicplayer.base_repository.converter.TrackViewModelConverter
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import io.reactivex.Single
import javax.inject.Inject

class ArtistInteractor @Inject constructor() {

    @Inject
    internal lateinit var tracksRepository: TracksRepository

    @Inject
    internal lateinit var trackViewModelConverter: TrackViewModelConverter

    @CheckResult
    internal fun listTracks(artistName: String): Single<List<TrackViewModel>> =
            tracksRepository.listTracks()
                    .map { tracks ->
                        tracks.filter {
                            it.user.username == artistName
                        }.map {
                            trackViewModelConverter.convert(it)
                        }
                    }

}
