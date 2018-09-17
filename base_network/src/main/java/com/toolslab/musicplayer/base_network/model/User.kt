package com.toolslab.musicplayer.base_network.model

import com.squareup.moshi.Json

data class User(

        @Json(name = "id")
        val id: String,

        @Json(name = "permalink")
        val permalink: String,

        @Json(name = "username")
        val username: String,

        @Json(name = "uri")
        val uri: String,

        @Json(name = "permalink_url")
        val permalinkUrl: String,

        @Json(name = "avatar_url")
        val avatarUrl: String
)
