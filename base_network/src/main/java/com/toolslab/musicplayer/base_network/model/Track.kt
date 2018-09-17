package com.toolslab.musicplayer.base_network.model

import com.squareup.moshi.Json

data class Track(

        @Json(name = "id")
        val id: String,

        @Json(name = "created_at")
        val createdAt: String,

        @Json(name = "release_date")
        val releaseDate: String,

        @Json(name = "release_timestamp")
        val releaseTimestamp: Int,

        @Json(name = "user_id")
        val userId: String,

        @Json(name = "duration")
        val duration: String,

        @Json(name = "permalink")
        val permalink: String,

        @Json(name = "description")
        val description: String,

        @Json(name = "geo")
        val geo: String,

        @Json(name = "tags")
        val tags: String,

        @Json(name = "taged_artists")
        val tagedArtists: String,

        @Json(name = "bpm")
        val bpm: String,

        @Json(name = "key")
        val key: String,

        @Json(name = "license")
        val license: String,

        @Json(name = "version")
        val version: String,

        @Json(name = "type")
        val type: String,

        @Json(name = "downloadable")
        val downloadable: String,

        @Json(name = "genre")
        val genre: String,

        @Json(name = "genre_slush")
        val genreSlush: String,

        @Json(name = "title")
        val title: String,

        @Json(name = "uri")
        val uri: String,

        @Json(name = "permalink_url")
        val permalinkUrl: String,

        @Json(name = "thumb")
        val thumb: String,

        @Json(name = "artwork_url")
        val artworkUrl: String,

        @Json(name = "artwork_url_retina")
        val artworkUrlRetina: String,

        @Json(name = "background_url")
        val backgroundUrl: String,

        @Json(name = "waveform_data")
        val waveformData: String,

        @Json(name = "waveform_url")
        val waveformUrl: String,

        @Json(name = "user")
        val user: User,

        @Json(name = "stream_url")
        val streamUrl: String,

        @Json(name = "preview_url")
        val previewUrl: String,

        @Json(name = "download_url")
        val downloadUrl: String,

        @Json(name = "playback_count")
        val playbackCount: String,

        @Json(name = "download_count")
        val downloadCount: String,

        @Json(name = "favoritings_count")
        val favoritingsCount: String,

        @Json(name = "reshares_count")
        val resharesCount: String,

        @Json(name = "comment_count")
        val commentCount: String,

        @Json(name = "played")
        val played: Boolean,

        @Json(name = "favorited")
        val favorited: Boolean,

        @Json(name = "reshared")
        val reshared: Boolean
)

