package com.toolslab.musicplayer.view.artists

import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.musicplayer.base_network.model.Track
import com.toolslab.musicplayer.base_network.model.User
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class ArtistsInteractorTest {

    private val mockTrack1: Track = mock()
    private val mockTrack2: Track = mock()
    private val mockTrack3: Track = mock()
    private val mockUser1: User = mock()
    private val mockUser2: User = mock()

    private val artist1 = "artist1"
    private val artist2 = "artist2"
    private val tracks = listOf(mockTrack1, mockTrack2, mockTrack3)

    private val underTest = ArtistsInteractor()

    @Before
    fun setUp() {
        whenever(mockTrack1.user).thenReturn(mockUser1)
        whenever(mockTrack2.user).thenReturn(mockUser2)
        whenever(mockTrack3.user).thenReturn(mockUser2)

        whenever(mockUser1.username).thenReturn(artist1)
        whenever(mockUser2.username).thenReturn(artist2)
    }

    @Test
    fun extractTrackCounts() {
        val trackCounts = underTest.extractTrackCounts(tracks)

        trackCounts.size shouldEqual 2
        trackCounts[artist1] shouldEqual 1
        trackCounts[artist2] shouldEqual 2
    }

}
