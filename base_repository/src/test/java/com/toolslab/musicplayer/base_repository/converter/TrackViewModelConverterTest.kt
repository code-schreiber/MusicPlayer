package com.toolslab.musicplayer.base_repository.converter

import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.musicplayer.base_network.model.Track
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class TrackViewModelConverterTest {

    private val mockTrack: Track = mock()

    private val title = "title"
    private val genre = "genre"
    private val duration = "270" // 270 seconds is 4m30s
    private val expectedDuration = "04:30"
    private val thumb = "thumb"
    private val streamUrl = "streamUrl"

    private val underTest = TrackViewModelConverter()

    @Before
    fun setUp() {
        underTest.durationConverter = DurationConverter()

        whenever(mockTrack.title).thenReturn(title)
        whenever(mockTrack.genre).thenReturn(genre)
        whenever(mockTrack.duration).thenReturn(duration)
        whenever(mockTrack.thumb).thenReturn(thumb)
        whenever(mockTrack.streamUrl).thenReturn(streamUrl)
    }

    @Test
    fun convert() {
        val trackViewModel = underTest.convert(mockTrack)

        trackViewModel.apply {
            title shouldEqual mockTrack.title
            genre shouldEqual mockTrack.genre
            trackDuration shouldEqual expectedDuration
            thumbnailImageUrl shouldEqual mockTrack.thumb
            streamUrl shouldEqual mockTrack.streamUrl
        }
    }

}
