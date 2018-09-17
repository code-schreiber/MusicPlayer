package com.toolslab.musicplayer.base_network


internal object ApiEndpoint {

    internal object Endpoint {
        const val TOP_TRACKS = "feed/?type=popular&page=1&count=20"
    }

    const val API_BASE_URL = "https://api-v2.hearthis.at/"

}
