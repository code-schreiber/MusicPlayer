package com.toolslab.musicplayer.base_repository.converter

import com.toolslab.musicplayer.base_network.model.Track
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import javax.inject.Inject

class TrackViewModelConverter @Inject constructor() : Converter<Track, TrackViewModel> {

    @Inject
    internal lateinit var durationConverter: DurationConverter

    override fun convert(source: Track) =
            TrackViewModel(
                    source.title,
                    source.genre,
                    durationConverter.convert(source.duration),
                    source.thumb,
                    source.streamUrl
            )

}
