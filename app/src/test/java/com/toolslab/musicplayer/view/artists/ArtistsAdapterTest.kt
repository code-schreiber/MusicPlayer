package com.toolslab.musicplayer.view.artists

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.musicplayer.R
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel
import kotlinx.android.synthetic.main.item_artist.view.*
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class ArtistsAdapterTest {

    private val viewModel1 = ArtistViewModel("name1", "url1")
    private val viewModel2 = ArtistViewModel("name2", "url2")
    private val viewModels = listOf(viewModel1, viewModel2)

    private val mockOnArtistClickListener: ArtistsAdapter.OnArtistClickListener = mock()
    private val mockView: View = mock()
    private val mockNameTextView: TextView = mock()
    private val mockTrackCountTextView: TextView = mock()
    private val mockThumbnailDraweeView: SimpleDraweeView = mock()
    private val mockContext: Context = mock()
    private val mockResources: Resources = mock()

    private val underTest = ArtistsAdapter(viewModels, mockOnArtistClickListener)

    @Before
    fun setUp() {
        viewModel1.trackCount = 1
        viewModel2.trackCount = 2
    }

    @Test
    fun onBindViewHolder() {
        val expectedTrackCountText = "expectedTrackCountText"
        whenever(mockView.item_artist_name).thenReturn(mockNameTextView)
        whenever(mockView.item_artist_track_count).thenReturn(mockTrackCountTextView)
        whenever(mockView.item_artist_thumbnail).thenReturn(mockThumbnailDraweeView)
        whenever(mockView.context).thenReturn(mockContext)
        whenever(mockContext.resources).thenReturn(mockResources)
        whenever(mockResources.getQuantityString(R.plurals.track_count, viewModel2.trackCount, viewModel2.trackCount)).thenReturn(expectedTrackCountText)
        val viewHolder = ArtistsAdapter.ViewHolder(mockView)

        underTest.onBindViewHolder(viewHolder, 1)

        verify(mockNameTextView).text = viewModel2.name
        verify(mockTrackCountTextView).text = expectedTrackCountText
        verify(mockThumbnailDraweeView).setImageURI(viewModel2.thumbnailImageUrl)
        verify(mockView).setOnClickListener(any())
    }

    @Test
    fun getItemCount() {
        underTest.itemCount shouldEqual viewModels.size
    }

}
