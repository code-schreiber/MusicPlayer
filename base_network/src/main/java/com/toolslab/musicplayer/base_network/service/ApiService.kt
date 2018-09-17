package com.toolslab.musicplayer.base_network.service

import com.toolslab.musicplayer.base_network.ApiEndpoint.Endpoint.TOP_TRACKS
import com.toolslab.musicplayer.base_network.model.Track
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(TOP_TRACKS)
    fun getTopTracks(): Single<List<Track>>

}
