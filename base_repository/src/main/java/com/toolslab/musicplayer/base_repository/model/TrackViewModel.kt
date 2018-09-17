package com.toolslab.musicplayer.base_repository.model

data class TrackViewModel(
        val title: String,
        val genre: String,
        val trackDuration: String,
        val thumbnailImageUrl: String,
        val streamUrl: String
)
