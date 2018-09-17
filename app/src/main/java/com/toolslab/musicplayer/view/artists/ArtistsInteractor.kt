package com.toolslab.musicplayer.view.artists

import android.support.annotation.CheckResult
import com.toolslab.musicplayer.base_network.model.Track
import com.toolslab.musicplayer.base_repository.TracksRepository
import com.toolslab.musicplayer.base_repository.converter.ArtistViewModelConverter
import javax.inject.Inject

class ArtistsInteractor @Inject constructor() {

    @Inject
    internal lateinit var tracksRepository: TracksRepository

    @Inject
    internal lateinit var artistViewModelConverter: ArtistViewModelConverter

    @CheckResult
    internal fun listArtists() =
            tracksRepository.listTracks()
                    .map { tracks ->
                        val trackCounts = extractTrackCounts(tracks)
                        tracks.map {
                            val artistViewModel = artistViewModelConverter.convert(it.user)
                            artistViewModel.trackCount = trackCounts.getValue(it.user.username)
                            artistViewModel
                        }
                                .distinct()
                    }

    internal fun extractTrackCounts(tracks: List<Track>): Map<String, Int> {
        val usernames = mutableMapOf<String, Int>()
        tracks
                .map { it.user.username }
                .forEach {
                    if (usernames.containsKey(it)) {
                        usernames[it] = usernames.getValue(it) + 1
                    } else {
                        usernames[it] = 1
                    }
                }
        return usernames.toMap()
    }

}
