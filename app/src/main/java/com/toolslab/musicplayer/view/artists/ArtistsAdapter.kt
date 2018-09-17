package com.toolslab.musicplayer.view.artists

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toolslab.musicplayer.R
import com.toolslab.musicplayer.base_repository.model.ArtistViewModel
import kotlinx.android.synthetic.main.item_artist.view.*

internal class ArtistsAdapter(private val viewModels: List<ArtistViewModel>, private val listener: OnArtistClickListener)
    : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    interface OnArtistClickListener {
        fun onArtistClicked(position: Int)
    }

    internal class ViewHolder(internal val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val viewModel = viewModels[position]
        viewHolder.view.apply {
            item_artist_name.text = viewModel.name
            item_artist_track_count.text = context.resources.getQuantityString(R.plurals.track_count, viewModel.trackCount, viewModel.trackCount)
            item_artist_thumbnail.setImageURI(viewModel.thumbnailImageUrl)
            setOnClickListener { listener.onArtistClicked(position) }
        }
    }

    override fun getItemCount() = viewModels.size

}
