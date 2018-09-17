package com.toolslab.musicplayer.view.artist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toolslab.musicplayer.R
import com.toolslab.musicplayer.base_repository.model.TrackViewModel
import kotlinx.android.synthetic.main.item_song.view.*

internal class ArtistAdapter(private val viewModels: List<TrackViewModel>, private val listener: OnSongClickListener)
    : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    interface OnSongClickListener {
        fun onSongClicked(position: Int)
    }

    internal class ViewHolder(internal val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val viewModel = viewModels[position]
        viewHolder.view.apply {
            item_song_title.text = viewModel.title
            item_song_genre.text = viewModel.genre
            item_song_track_duration.text = viewModel.trackDuration
            item_song_thumbnail.setImageURI(viewModel.thumbnailImageUrl)
            setOnClickListener { listener.onSongClicked(position) }
        }
    }

    override fun getItemCount() = viewModels.size

}
