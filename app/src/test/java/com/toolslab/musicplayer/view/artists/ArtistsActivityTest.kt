package com.toolslab.musicplayer.view.artists

import android.support.v7.widget.LinearLayoutManager
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test


class ArtistsActivityTest {

    private val viewModel1 = ArtistViewModel("name1", "url1")
    private val viewModel2 = ArtistViewModel("name2", "url2")
    private val viewModels = listOf(viewModel1, viewModel2)

    private val underTest = ArtistsActivity()

    @Before
    fun setUp() {
        underTest.recyclerView = mock()
        underTest.presenter = mock()
    }

    @Test
    fun setViewModels() {
        val captor = argumentCaptor<ArtistsAdapter>()

        underTest.setViewModels(viewModels)

        verify(underTest.recyclerView).setHasFixedSize(true)
        verify(underTest.recyclerView).layoutManager = any<LinearLayoutManager>()
        verify(underTest.recyclerView).adapter = captor.capture()
        captor.firstValue.itemCount shouldEqual viewModels.size
    }

    @Test
    fun onArtistClicked() {
        val position = 2

        underTest.onArtistClicked(position)

        verify(underTest.presenter).onArtistClicked(position)
    }

}
