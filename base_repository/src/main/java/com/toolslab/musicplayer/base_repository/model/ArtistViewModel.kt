package com.toolslab.musicplayer.base_repository.model

data class ArtistViewModel(
        val name: String,
        val thumbnailImageUrl: String
) {
    var trackCount = 0
}
