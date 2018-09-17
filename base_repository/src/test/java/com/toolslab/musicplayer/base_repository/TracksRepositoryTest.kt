package com.toolslab.musicplayer.base_repository

import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.musicplayer.base_network.model.Track
import io.reactivex.Single
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class TracksRepositoryTest {

    private val mockTracks: List<Track> = mock()

    private val underTest = TracksRepository()

    @Before
    fun setUp() {
        underTest.apiService = mock()
        underTest.errorHandler = mock()
    }

    @Test
    fun listTracks() {
        whenever(underTest.apiService.getTopTracks()).thenReturn(Single.just(mockTracks))

        val result = underTest.listTracks().blockingGet()

        result shouldEqual mockTracks
        verifyZeroInteractions(underTest.errorHandler)
    }

    @Test
    fun listTracksWithError() {
        val exception = Exception()
        val handledException = Exception("a handled exception")
        whenever(underTest.apiService.getTopTracks()).thenReturn(Single.error(exception))
        whenever(underTest.errorHandler.handle<Track>(exception)).thenReturn(Single.error(handledException))

        val testObserver = underTest.listTracks().test()
        testObserver.awaitTerminalEvent()

        testObserver.values() shouldEqual emptyList()
        testObserver.errorCount() shouldEqual 1
        testObserver.errors()[0] shouldEqual handledException
    }

}
